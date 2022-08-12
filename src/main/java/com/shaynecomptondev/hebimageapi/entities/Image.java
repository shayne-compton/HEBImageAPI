package com.shaynecomptondev.hebimageapi.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Lob
    private byte[] content;
    private Date createDate;
    private boolean isActive;
}
