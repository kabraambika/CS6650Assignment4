package com.hw2.Server.repository;

import com.hw2.Server.model.Album;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
