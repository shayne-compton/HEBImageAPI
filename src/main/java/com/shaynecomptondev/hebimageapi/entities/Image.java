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
}
