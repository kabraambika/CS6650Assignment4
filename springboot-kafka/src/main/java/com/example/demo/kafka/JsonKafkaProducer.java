package com.example.demo.kafka;

import com.example.demo.model.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author ambikakabra
 */
@Service
public class JsonKafkaProducer {
  private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

  private KafkaTemplate<String, Review> kafkaTemplate;

  public JsonKafkaProducer(KafkaTemplate<String, Review> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(Review review) {
    LOGGER.info(String.format("Message sent -> %s", review.toString()));
    Message<Review> message = MessageBuilder.withPayload(review).setHeader(KafkaHeaders.TOPIC, "albumReviewsNew1").build();

    kafkaTemplate.send(message);
  }
}
