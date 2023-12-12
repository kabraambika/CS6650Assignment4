package com.example.demo.kafka;

import com.example.demo.model.AlbumReview;
import com.example.demo.model.Review;
import com.example.demo.service.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ambikakabra
 */
@Service
public class JsonKafkaConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
  private AlbumService albumService;
  private String EC2_PUBLIC_IP = "ec2-54-69-107-141.us-west-2.compute.amazonaws.com";
  JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), EC2_PUBLIC_IP, 6379);

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

    try (Jedis jedis = jedisPool.getResource()) {
      jedis.set(metaData.getAlbumID(), String.valueOf(metaData.getLikes()));
      int ttl = 30;
      jedis.expire(metaData.getId(), ttl);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
