package Servlets;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import models.AlbumInfo;
import models.Response;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.sql.*;
import java.util.List;
import java.util.concurrent.TimeoutException;

@WebServlet(name = "Servlets.AlbumsServlet", value = "/Servlets.AlbumsServlet")
public class AlbumsServlet extends HttpServlet {
    Gson gson = new Gson();
    String connectionString = "mongodb://ec2-54-68-149-246.us-west-2.compute.amazonaws.com:27017";
    MongoClient mongoClient = MongoClients.create(connectionString);
    MongoDatabase database = mongoClient.getDatabase("hw3");
    MongoCollection<Document> collection = database.getCollection("albums");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(request);
            byte[] imageBytes = new byte[0];
            AlbumInfo album = null;

            for (FileItem item : items) {
                switch (item.getFieldName()) {
                    case "profile": {
                        album = gson.fromJson(item.getString(), AlbumInfo.class);
                        break;
                    }
                    case "image": {
                        imageBytes = item.get();
                        break;
                    }
                    default:
                        break;
                }
            }

            Document document = new Document("artist", album.getArtist()).append("title", album.getTitle()).append("year", album.getYear()).append("likes", album.getLikes()).append("image", imageBytes);
            InsertOneResult result = collection.insertOne(document);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(
                    gson.toJson(
                            new Response(result.getInsertedId().asObjectId().getValue().toString(), imageBytes.length)
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isUrlValid(String[] urlPath) {
        return urlPath.length >= 1;
    }
}