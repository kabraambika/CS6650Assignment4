package com.hw2.Server.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "albums")
public class Album {
  @Id
  private String albumId;
  private String artist;
  private String title;
  private String year;

  private byte[] image;

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public void setAlbumId(String albumId) {
    this.albumId = albumId;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getAlbumId() {
    return albumId;
  }

  public String getArtist() {
    return artist;
  }

  public String getTitle() {
    return title;
  }

  public String getYear() {
    return year;
  }
}
