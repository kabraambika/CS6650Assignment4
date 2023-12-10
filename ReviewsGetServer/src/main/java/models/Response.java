package models;

public class Response {
    private String albumId;
    String likes;

    public Response(String albumId, String likes) {
        this.albumId = albumId;
        this.likes = likes;
    }
}
