package com.hw2.Server.service;

import com.hw2.Server.model.Album;
import com.hw2.Server.model.ImageMetaData;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public interface AlbumService {
  ImageMetaData createAlbum(Album album);

  Album getAlbum(String albumId);
}
