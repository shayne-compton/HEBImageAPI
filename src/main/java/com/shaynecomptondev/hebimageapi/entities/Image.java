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
    private Date createDate;
    private boolean isActive;
    @OneToMany(mappedBy = "image", cascade = {CascadeType.ALL})
    private Set<ImageMetadata> imageMetadata;
    @OneToMany(mappedBy = "image", cascade = {CascadeType.ALL})
    private Set<ImageObject> imageObjects;

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
}
