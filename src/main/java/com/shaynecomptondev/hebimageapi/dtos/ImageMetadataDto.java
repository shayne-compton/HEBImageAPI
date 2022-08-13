package com.shaynecomptondev.hebimageapi.dtos;

public class ImageMetadataDto {
    private String Id;
    private String Name;
    private String Value;

    public ImageMetadataDto() { }

    public ImageMetadataDto(String id, String name, String value) {
        Id = id;
        Name = name;
        Value = value;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public static ImageMetadataDtoBuilder build() { return new ImageMetadataDtoBuilder(); }

    public static class ImageMetadataDtoBuilder {
        private String Id;
        private String Name;
        private String Value;

        public ImageMetadataDtoBuilder setId(String id) {
            Id = id;
            return this;
        }

        public ImageMetadataDtoBuilder setName(String name) {
            Name = name;
            return this;
        }

        public ImageMetadataDtoBuilder setValue(String value) {
            Value = value;
            return this;
        }

        public ImageMetadataDto build() { return new ImageMetadataDto(Id, Name, Value); }
    }
}
