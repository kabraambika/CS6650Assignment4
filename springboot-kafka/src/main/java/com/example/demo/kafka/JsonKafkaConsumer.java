package com.example.demo.kafka;

import com.example.demo.model.AlbumReview;
import com.example.demo.model.Review;
import com.example.demo.service.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author ambikakabra
 */
@Service
public class JsonKafkaConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
  private AlbumService albumService;

  public JsonKafkaConsumer(AlbumService albumService) {
    this.albumService = albumService;
  }

  @KafkaListener(topics = "albumReviewsNew1")
  public void consume(Review review){
    LOGGER.info(String.format("Json message recieved -> %s", review.toString()));

    AlbumReview newReview = new AlbumReview();
    newReview.setAlbumID(review.getAlbumID());
    newReview.setLikes(review.getLikes());
    AlbumReview metaData = albumService.createReview(newReview);

  }
}
