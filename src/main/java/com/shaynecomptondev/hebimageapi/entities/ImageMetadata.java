package com.shaynecomptondev.hebimageapi.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ImageMetadata {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String value;
    private Date createDate;
    private boolean isActive;
    @ManyToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name="image_id")
    private Image image;

    public ImageMetadata() { }

    private ImageMetadata(Integer id, String name, String value, Date createDate, boolean isActive, Image image) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.createDate = createDate;
        this.isActive = isActive;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public static ImageMetadataBuilder builder() { return new ImageMetadataBuilder(); }

    public static class ImageMetadataBuilder {
        private Integer id;
        private String name;
        private String value;
        private Date createDate;
        private boolean isActive;
        private Image image;

        public ImageMetadataBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public ImageMetadataBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ImageMetadataBuilder setValue(String value) {
            this.value = value;
            return this;
        }

        public ImageMetadataBuilder setCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public ImageMetadataBuilder setActive(boolean active) {
            isActive = active;
            return this;
        }

        public ImageMetadataBuilder setImage(Image image) {
            this.image = image;
            return this;
        }

        public ImageMetadata build() { return new ImageMetadata(id, name, value, createDate, isActive, image); }
    }
}
