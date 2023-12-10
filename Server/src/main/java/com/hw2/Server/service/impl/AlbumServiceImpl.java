package com.hw2.Server.service.impl;

import com.hw2.Server.model.Album;
import com.hw2.Server.model.ImageMetaData;
import com.hw2.Server.repository.AlbumRepository;
import com.hw2.Server.service.AlbumService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {
  @Autowired
  AlbumRepository albumRepository;
  @Override
  public ImageMetaData createAlbum(Album album) {
    Album savedAlbum = albumRepository.save(album);

    return new ImageMetaData(savedAlbum.getAlbumId(), savedAlbum.getImage().length);
  }
  @Override
  public Album getAlbum(String albumId) {
    return albumRepository.findById(albumId).get();
  }
}
