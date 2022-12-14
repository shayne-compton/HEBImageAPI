package com.shaynecomptondev.hebimageapi.services;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.shaynecomptondev.hebimageapi.dtos.ImageDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageMetadataDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageObjectsDto;
import com.shaynecomptondev.hebimageapi.dtos.ImageUploadDto;
import com.shaynecomptondev.hebimageapi.entities.Image;
import com.shaynecomptondev.hebimageapi.entities.ImageMetadata;
import com.shaynecomptondev.hebimageapi.entities.ImageObject;
import com.shaynecomptondev.hebimageapi.exceptions.ImageNotFoundException;
import com.shaynecomptondev.hebimageapi.exceptions.InvalidParameterException;
import com.shaynecomptondev.hebimageapi.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;


/**
 * Abstraction layer providing functions to interact with external infrastructure
 * such as databases and third party APIs
 *
 * @author Shayne Compton
 *
 */
public class ImageServiceImpl implements ImageService {

    /**
     * Repository for CRUD operations dealing with Image entities
     */
    @Autowired
    private ImageRepository imageRepository;


    /**
     * Provides image detection capabilities
     */
    @Autowired
    private ImageAnalyzer imageAnalyzer;


    /**
     * Provides functions to download files from external webpages
     */
    @Autowired
    private DownloadService downloadService;

    /**
     * <p>Gets all active persisted images</p>
     * @return all active images from database
     */
    @Override
    public Iterable<ImageDto> GetAllImages() {
        Iterable<Image> images = imageRepository.findAllActive();
        ArrayList<ImageDto> imageDtos = new ArrayList<>();
        for (Image image : images)
        {
            imageDtos.add(MapEntityToDto(image));
        }
        return imageDtos;
    }

    /**
     * <p>Gets all active persisted images for a specified object query</p>
     * @param objects a comma separated string containing objects to search for (ex: "dog","cat")
     * @return all active images that match object criteria
     */
    @Override
    public Iterable<ImageDto> GetImagesByObjects(String objects) {
        //strip quotes of beginning and end of string, if they exist
        objects = objects.replaceAll("^\"|\"$", "");
        String[] tokenizedObjects = objects.split(",");
        Iterable<Image> images = imageRepository.findAllByObjectActive(tokenizedObjects);
        ArrayList<ImageDto> imageDtos = new ArrayList<>();
        for (Image image : images)
        {
            imageDtos.add(MapEntityToDto(image));
        }
        return imageDtos;
    }

    /**
     * <p>Returns a single persisted image for a specified id, if it exists</p>
     *
     * @param imageId the id of the image to return
     * @return the image that belongs to the specified Id
     * @throws ImageNotFoundException when image does not exist
     * @throws InvalidParameterException
     */
    @Override
    public ImageDto GetImageById(int imageId) {
        if (imageId < 1) {
            throw new InvalidParameterException("imageId must be a positive integer");
        }
        Optional<Image> image = imageRepository.findById(imageId);
        if (image.isPresent()) {
            ImageDto imageDto = MapEntityToDto(image.get());
            return imageDto;
        } else {
            throw new ImageNotFoundException(imageId);
        }
    }

    /**
     * <p>Processes a byte representation of an image from either a file or imageUrl, according to specified parameter.
     * Metadata will be extracted from the image.
     * Detected objects in the image will be returned if specified by caller.
     * Image, metadata, and any detected objects will be persisted to the database.
     *
     * Upon successful processing, all persisted image information will be returned to caller.</p>
     *
     * @param image criteria specified regarding how to process an uploaded image
     * @return representation of the image that was persisted, including any metadata that was obtained
     * @throws InvalidParameterException
     */
    @Override
    public ImageDto SaveImage(ImageUploadDto image) {
        ValidateImageUploadDto(image);
        boolean shouldDetectObjects = image.isEnableObjectDetection();
        //todo check if image is from url or byte array
        byte[] imageContents = null;
        String imageUrl = image.getImageUrl();
        if (imageUrl != null)
        {
            imageContents = downloadService.DownloadFileFromUrl(imageUrl);
        }
        else
        {
            imageContents = image.getImage();
        }

        Iterable<ImageMetadataDto> imageMetadata = this.GetImageMetadata(imageContents);

        //Detect objects if specified
        Iterable<ImageObjectsDto> detectedObjects = null;
        if (shouldDetectObjects)
        {
            detectedObjects = imageAnalyzer.DetectObjects(imageContents);
        }
        String imageLabel = GetImageLabel(image.getLabel(), detectedObjects);
        image.setLabel(imageLabel);
        //save our image
        Image newImage = MapUploadToEntity(image, imageContents, detectedObjects, imageMetadata);
        newImage = imageRepository.save(newImage);
        ImageDto newImageDto = MapEntityToDto(newImage);
        return newImageDto;
    }

    private void ValidateImageUploadDto(ImageUploadDto image) {
        if (image == null) {
            throw new InvalidParameterException("image upload must not be null");
        }
        if (image.getImageUrl() != null && image.getImage() != null) {
            throw new InvalidParameterException("please only provide either an image url or an image");
        }
        if (image.getImageUrl() == null && image.getImage() == null) {
            throw new InvalidParameterException("please provide either an image or image url");
        }
    }

    private String GetImageLabel(String label, Iterable<ImageObjectsDto> detectedObjects) {
        if ((label == null || label == "") && detectedObjects != null) {
            double currentHighScore = 0;
            double currentImageScore = 0;
            String generatedLabel = "";
            for (ImageObjectsDto imageObjectsDto : detectedObjects) {
                currentImageScore = Double.parseDouble(imageObjectsDto.getScore());
                if (currentImageScore > currentHighScore) {
                    generatedLabel = imageObjectsDto.getName();
                }
            }
            label = generatedLabel;
        }

        return label;
    }

    private ImageDto MapEntityToDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setContent(image.getContent());
        imageDto.setLabel(image.getLabel());
        //map metadata
        if (image.getImageMetadata() != null) {
            ArrayList<ImageMetadataDto> metadataDtos = new ArrayList<>();
            ImageMetadataDto tempMetadataDto = null;
            for (ImageMetadata metadata : image.getImageMetadata()) {
                tempMetadataDto = new ImageMetadataDto();
                tempMetadataDto.setId(metadata.getId().toString());
                tempMetadataDto.setName(metadata.getName());
                tempMetadataDto.setValue(metadata.getValue());
                metadataDtos.add(tempMetadataDto);
            }
            imageDto.setMetadata(metadataDtos.toArray(new ImageMetadataDto[metadataDtos.size()]));
        }

        //map objects
        if (image.getImageObjects() != null) {
            ArrayList<ImageObjectsDto> detectedObjectDtos = new ArrayList<>();
            ImageObjectsDto tempImageObjectDto = null;
            for (ImageObject imageObject : image.getImageObjects()) {
                tempImageObjectDto = new ImageObjectsDto();
                tempImageObjectDto.setId(imageObject.getId().toString());
                tempImageObjectDto.setName(imageObject.getName());
                tempImageObjectDto.setScore(imageObject.getScore().toString());
                detectedObjectDtos.add(tempImageObjectDto);
            }
            imageDto.setDetectedObjects(detectedObjectDtos.toArray(new ImageObjectsDto[detectedObjectDtos.size()]));
        }
        return imageDto;
    }

    private Image MapUploadToEntity(ImageUploadDto imageDto, byte[] imageContent, Iterable<ImageObjectsDto> detectedObjectDtos, Iterable<ImageMetadataDto> metadataDtos) {
        Date today = new Date();
        today.setHours(0);

        //map image
        Image image = new Image();
        image.setContent(imageContent);
        image.setActive(true);
        image.setCreateDate(today);
        image.setLabel(imageDto.getLabel());

        String imageUrl = imageDto.getImageUrl();
        if (imageUrl != null && imageDto.getImage() == null) {
            image.setSource(imageUrl);
        } else {
            image.setSource("File");
        }

        //map metadata
        Set<ImageMetadata> metadata = new HashSet<>();
        ImageMetadata tempMetadata = null;
        if (metadataDtos != null) {
            for (ImageMetadataDto metadataDto : metadataDtos) {
                tempMetadata = new ImageMetadata();
                tempMetadata.setName(metadataDto.getName());
                tempMetadata.setValue(metadataDto.getValue());
                tempMetadata.setCreateDate(today);
                tempMetadata.setActive(true);
                tempMetadata.setImage(image);
                metadata.add(tempMetadata);
            }
            image.setImageMetadata(metadata);
        }

        //map detected objects
        Set<ImageObject> mappedObjects = new HashSet<>();
        ImageObject tempImageObject = null;
        if (detectedObjectDtos != null) {
            for (ImageObjectsDto detectedObjectsDto : detectedObjectDtos) {
                tempImageObject = new ImageObject();
                tempImageObject.setName(detectedObjectsDto.getName());
                tempImageObject.setScore(Double.parseDouble(detectedObjectsDto.getScore()));
                tempImageObject.setCreateDate(today);
                tempImageObject.setActive(true);
                tempImageObject.setImage(image);
                mappedObjects.add(tempImageObject);
            }
            image.setImageObjects(mappedObjects);
        }

        return image;
    }

    /**
     * <p>Processes a byte[] representation of an image file and returns all extracted metadata
     *
     * Metadata is obtained using the following library:
     * <a href="https://github.com/drewnoakes/metadata-extractor">https://github.com/drewnoakes/metadata-extractor</a>
     * </p>
     *
     * @param imageContent a byte array representation of an image file
     * @return all metadata extracted from the image file
     */
    public Iterable<ImageMetadataDto> GetImageMetadata(byte[] imageContent) {
        ArrayList<ImageMetadataDto> metadataDtos = new ArrayList<>();
        ImageMetadataDto tempMetadataDto = null;
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(new ByteArrayInputStream(imageContent));
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    String[] properties = tag.toString().split(" - ");
                    tempMetadataDto = new ImageMetadataDto();
                    tempMetadataDto.setName(properties[0]);
                    if (properties.length != 2) {
                        tempMetadataDto.setValue("");
                    }
                    else {
                        tempMetadataDto.setValue(properties[1]);
                    }
                    metadataDtos.add(tempMetadataDto);
                }
            }
        } catch (ImageProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return metadataDtos;
    }
}
