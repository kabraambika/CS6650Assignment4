package com.example.demo.controller;

import com.example.demo.kafka.JsonKafkaProducer;
import com.example.demo.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ambikakabra
 */
@RestController
@RequestMapping("review/")
public class JsonMessageController {
  private JsonKafkaProducer kafkaProducer;

  public JsonMessageController(JsonKafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @PostMapping("/{likeOrNot}/{albumID}")
  public ResponseEntity<String> publish(@PathVariable String likeOrNot, @PathVariable String albumID){
    Review review = new Review();
    review.setAlbumID(albumID);
    if(likeOrNot.equals("like"))
      review.setLikes(1);
    else if(likeOrNot.equals("dislike"))
      review.setLikes(-1);
    kafkaProducer.sendMessage(review);
    return ResponseEntity.ok("review sent to kafka topic");
  }
}
