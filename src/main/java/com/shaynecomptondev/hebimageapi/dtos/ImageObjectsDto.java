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
}
