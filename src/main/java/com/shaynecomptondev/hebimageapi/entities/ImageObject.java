package com.shaynecomptondev.hebimageapi.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Shayne Compton
 */
@Entity
public class ImageObject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double score;
    private Date createDate;
    private boolean isActive;
    @ManyToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name="image_id")
    private Image image;

    public ImageObject() { }

    private ImageObject(Integer id, String name, Double score, Date createDate, boolean isActive, Image image) {
        this.id = id;
        this.name = name;
        this.score = score;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public static ImageObjectBuilder builder() { return new ImageObjectBuilder(); }

    public static class ImageObjectBuilder {

        private Integer id;
        private String name;
        private Double score;
        private Date createDate;
        private boolean isActive;
        private Image image;

        public ImageObjectBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public ImageObjectBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ImageObjectBuilder setScore(Double score) {
            this.score = score;
            return this;
        }

        public ImageObjectBuilder setCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public ImageObjectBuilder setActive(boolean active) {
            isActive = active;
            return this;
        }

        public ImageObjectBuilder setImage(Image image) {
            this.image = image;
            return this;
        }

        public ImageObject build() { return new ImageObject(id, name, score, createDate, isActive, image); }
    }
}
