package com.shaynecomptondev.hebimageapi.repositories;

import com.shaynecomptondev.hebimageapi.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * CRUD repository representing Image entities
 *
 * @Author Shayne Compton
 */
public interface ImageRepository extends JpaRepository<Image, Integer> {
    /**
     * Uses a native SQL query to return active images.
     * @return All active objects
     */
    @Query(value = "SELECT * FROM image WHERE is_active = 1", nativeQuery = true)
    List<Image> findAllActive();

    /**
     * Uses a native SQL query to return active images that match specified detected object criteria
     * @param objects string array representing detected objects to filter
     * @return All active images that match specified filter
     */
    @Query(value =  "Select distinct i.*\n" +
                    "From image i\n" +
                    "Inner Join image_object o on o.image_id = i.id\n" +
                    "Where o.name in (?1) And i.is_active;", nativeQuery = true)
    List<Image> findAllByObjectActive(String[] objects);
}
