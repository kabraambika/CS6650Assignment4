package models;

public class Response {
    private String albumID;
    private long imageSize;

    public Response(String albumID, int imageSize) {
        this.albumID = albumID;
        this.imageSize = imageSize;
    }
}
