package com.shaynecomptondev.hebimageapi.dtos;

public class ImageUploadDto {
    private byte[] image;
    private String imageUrl;
    private String label;
    private boolean enableObjectDetection;

    public ImageUploadDto() { }

    private ImageUploadDto(byte[] image, String imageUrl, String label, boolean enableObjectDetection) {
        this.image = image;
        this.imageUrl = imageUrl;
        this.label = label;
        this.enableObjectDetection = enableObjectDetection;
    }

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

    public static ImageUploadDtoBuilder builder() {
        return new ImageUploadDtoBuilder();
    }

    public static class ImageUploadDtoBuilder {
        private byte[] image;
        private String imageUrl;
        private String label;
        private boolean enableObjectDetection;

        public ImageUploadDtoBuilder setImage(byte[] image) {
            this.image = image;
            return this;
        }

        public ImageUploadDtoBuilder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public ImageUploadDtoBuilder setLabel(String label) {
            this.label = label;
            return this;
        }

        public ImageUploadDtoBuilder setEnableObjectDetection(boolean isActive) {
            this.enableObjectDetection = isActive;
            return this;
        }

        public ImageUploadDto build() {
            return new ImageUploadDto(image, imageUrl, label, enableObjectDetection);
        }
    }
}
