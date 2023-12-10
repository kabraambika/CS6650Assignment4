package com.hw2.Server.model;

import java.util.HashMap;
import java.util.Map;

public class ImageMetaData {
  private String albumId;

  private Integer imageSize;

  public ImageMetaData(String albumId, Integer imageSize) {
    this.imageSize = imageSize;
    this.albumId = albumId;
  }

  public Map<String, Object> getOutput() {
    return Map.of("albumID", this.albumId, "imageSize", this.imageSize);
  }
}
