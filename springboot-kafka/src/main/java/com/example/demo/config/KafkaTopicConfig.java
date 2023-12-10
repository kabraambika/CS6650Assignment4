package com.example.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author ambikakabra
 */
@Configuration
public class KafkaTopicConfig {

  @Bean
  public NewTopic albumReviewsTopic() {
    return TopicBuilder.name("albumReviews").build();
  }

  @Bean
  public NewTopic albumReviewsJsonsTopic() {
    return TopicBuilder.name("albumReviewsNew1").partitions(3).replicas(1).build();
  }
}
