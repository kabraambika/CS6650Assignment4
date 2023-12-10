package com.example.demo.service;


import com.example.demo.model.AlbumReview;
import org.springframework.stereotype.Service;

/**
 * @author ambikakabra
 */
@Service
public interface AlbumService {
  AlbumReview createReview(AlbumReview review);
}
