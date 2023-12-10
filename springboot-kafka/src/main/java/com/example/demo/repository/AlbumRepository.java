package com.example.demo.repository;


import com.example.demo.model.AlbumReview;
import com.example.demo.model.Review;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ambikakabra
 */
public interface AlbumRepository extends MongoRepository<AlbumReview, String> {
  Optional<AlbumReview> findByAlbumID(String albumID);
}
