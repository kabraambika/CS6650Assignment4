package com.example.demo.service.impl;


import com.example.demo.model.AlbumReview;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.service.AlbumService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ambikakabra 
 */
@Service
public class AlbumServiceImpl implements AlbumService {
  @Autowired
  AlbumRepository albumRepository;

  @Override
  public AlbumReview createReview(AlbumReview review) {
    // Check if a review with the same albumID already exists
    Optional<AlbumReview> existingReviewOptional = albumRepository.findByAlbumID(review.getAlbumID());

    if (existingReviewOptional.isPresent()) {
      // Update the existing review
      AlbumReview existingReview = existingReviewOptional.get();
      existingReview.setLikes(existingReview.getLikes() + review.getLikes());

      return albumRepository.save(existingReview);
    } else {
      // Save a new review
      return albumRepository.save(review);
    }
  }
}
