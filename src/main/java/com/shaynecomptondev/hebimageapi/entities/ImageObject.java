package com.shaynecomptondev.hebimageapi.entities;

import javax.persistence.*;
import java.util.Date;

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
}
