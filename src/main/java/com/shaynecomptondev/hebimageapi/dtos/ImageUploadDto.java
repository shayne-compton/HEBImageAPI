package com.shaynecomptondev.hebimageapi.dtos;

public class ImageUploadDto {
    private byte[] image;
    private String imageUrl;
    private String label;
    private boolean enableObjectDetection;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isEnableObjectDetection() {
        return enableObjectDetection;
    }

    public void setEnableObjectDetection(boolean enableObjectDetection) {
        this.enableObjectDetection = enableObjectDetection;
    }
}
