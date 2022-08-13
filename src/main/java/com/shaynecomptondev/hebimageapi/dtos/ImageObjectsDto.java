package com.shaynecomptondev.hebimageapi.dtos;

public class ImageObjectsDto {
    private String id;
    private String name;
    private String score;

    public ImageObjectsDto(String id, String name, String score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public ImageObjectsDto() { }

    public ImageObjectsDto(String name, String score)
    {
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public static ImageObjectsDtoBuilder builder() { return new ImageObjectsDtoBuilder(); }

    public static class ImageObjectsDtoBuilder {
        private String id;
        private String name;
        private String score;

        public ImageObjectsDtoBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ImageObjectsDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ImageObjectsDtoBuilder setScore(String score) {
            this.score = score;
            return this;
        }

        public ImageObjectsDto build() { return new ImageObjectsDto(id, name, score); }
    }
}
