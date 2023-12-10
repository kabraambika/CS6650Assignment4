package com.hw2.Server.controller;

import com.hw2.Server.model.Album;
import com.hw2.Server.model.AlbumProfile;
import com.hw2.Server.model.ImageMetaData;
import com.hw2.Server.service.AlbumService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class AlbumController {
  @Autowired
  AlbumService albumService;

  @RequestMapping(value = "/albums" , method = RequestMethod.POST, consumes = { "multipart/form-data" })
  public ResponseEntity<Map<String, Object>> saveAlbum(@ModelAttribute AlbumProfile albumProfile){
    try{
      String title = albumProfile.getTitle();
      String artist = albumProfile.getArtist();
      String year = albumProfile.getYear();
      MultipartFile imagePath = albumProfile.getImage();

      if(validateRequestData(title, artist, year, imagePath)) {
        byte[] imageBytes = imagePath.getBytes();

        Album album = new Album();
        album.setTitle(title);
        album.setArtist(artist);
        album.setYear(year);
        album.setImage(imageBytes);

        ImageMetaData metaData = albumService.createAlbum(album);

        return new ResponseEntity<>(metaData.getOutput(), HttpStatus.CREATED);
      }
      else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private Boolean validateRequestData(String title, String artist, String year, MultipartFile image) {
    return !title.equals("") && !artist.equals("") && !year.equals("") && image.getSize() != 0;
  }

  @GetMapping("/albums/{albumId}")
  public Map<String, Object> getAlbumById(@PathVariable String albumId){
    Album album = albumService.getAlbum(albumId);
    return Map.of("artist", album.getArtist(), "title", album.getTitle(), "year", album.getYear());
  }
}