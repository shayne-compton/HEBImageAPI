package com.shaynecomptondev.hebimageapi.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Lob
    private byte[] content;
    private String source;
    private String label;
    private Date createDate;
    private boolean isActive;
    @OneToMany(mappedBy = "image", cascade = {CascadeType.ALL})
    private Set<ImageMetadata> imageMetadata;
    @OneToMany(mappedBy = "image", cascade = {CascadeType.ALL})
    private Set<ImageObject> imageObjects;

    public Image() { }

    private Image(Integer id, byte[] content, String source, String label, Date createDate, boolean isActive, Set<ImageMetadata> imageMetadata, Set<ImageObject> imageObjects) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.label = label;
        this.createDate = createDate;
        this.isActive = isActive;
        this.imageMetadata = imageMetadata;
        this.imageObjects = imageObjects;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public Set<ImageMetadata> getImageMetadata() {
        return imageMetadata;
    }

    public void setImageMetadata(Set<ImageMetadata> imageMetadata) {
        this.imageMetadata = imageMetadata;
    }

    public Set<ImageObject> getImageObjects() {
        return imageObjects;
    }

    public void setImageObjects(Set<ImageObject> imageObjects) {
        this.imageObjects = imageObjects;
    }

    public static ImageBuilder builder() {
        return new ImageBuilder();
    }

    public static class ImageBuilder {
        private Integer id;
        private byte[] content;
        private String source;
        private String label;
        private Date createDate;
        private boolean isActive;
        private Set<ImageMetadata> imageMetadata;
        private Set<ImageObject> imageObjects;

        public ImageBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public ImageBuilder setContent(byte[] content) {
            this.content = content;
            return this;
        }

        public ImageBuilder setSource(String source) {
            this.source = source;
            return this;
        }

        public ImageBuilder setLabel(String label) {
            this.label = label;
            return this;
        }

        public ImageBuilder setCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public ImageBuilder setActive(boolean active) {
            isActive = active;
            return this;
        }

        public ImageBuilder setImageMetadata(Set<ImageMetadata> imageMetadata) {
            this.imageMetadata = imageMetadata;
            return this;
        }

        public ImageBuilder setImageObjects(Set<ImageObject> imageObjects) {
            this.imageObjects = imageObjects;
            return this;
        }

        public Image build() { return new Image(id, content, source, label, createDate, isActive, imageMetadata, imageObjects);}
    }
}
