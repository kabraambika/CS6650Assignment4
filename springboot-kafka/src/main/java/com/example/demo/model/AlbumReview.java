package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ambikakabra
 */
@Document(collection = "reviews")
public class AlbumReview {
  @Id
  private String id;
  private String albumID;
  private int likes;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAlbumID() {
    return albumID;
  }

  public void setAlbumID(String albumID) {
    this.albumID = albumID;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  @Override
  public String toString() {
    return "AlbumReview{" +
        "id='" + id + '\'' +
        ", albumID='" + albumID + '\'' +
        ", likes=" + likes +
        '}';
  }
}
