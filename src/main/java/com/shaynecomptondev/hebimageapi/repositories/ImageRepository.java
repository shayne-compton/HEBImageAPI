package com.shaynecomptondev.hebimageapi.repositories;

import com.shaynecomptondev.hebimageapi.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Query(value = "SELECT * FROM image WHERE is_active = 1", nativeQuery = true)
    List<Image> findAllActive();

    @Query(value =  "Select distinct i.*\n" +
                    "From image i\n" +
                    "Inner Join image_object o on o.image_id = i.id\n" +
                    "Where o.name in (?1) And i.is_active;", nativeQuery = true)
    List<Image> findAllByObjectActive(String[] objects);
}
