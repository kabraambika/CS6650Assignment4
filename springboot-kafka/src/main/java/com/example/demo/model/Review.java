package com.example.demo.model;

/**
 * @author ambikakabra
 */
public class Review {
  private String albumID;
  private int likes;

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
    return "Review{" +
        "albumID='" + albumID + '\'' +
        ", likes=" + likes +
        '}';
  }
}
