package Servlets;

import com.google.gson.Gson;
import models.Response;
import org.bson.Document;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import com.mongodb.client.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ReviewsGetServlet", value = "/ReviewsGetServlet")
public class ReviewsGetServlet extends HttpServlet {
    Gson gson = new Gson();
    String EC2_URL = "ec2-54-69-107-141.us-west-2.compute.amazonaws.com";
    private String mongoConnectionString = "mongodb://ec2-54-68-149-246.us-west-2.compute.amazonaws.com:27017";
    private String mongoDatabase = "AlbumStore";
    private String collectionName = "reviews";
    JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), EC2_URL, 6379);
    MongoClient mongoClient = MongoClients.create(mongoConnectionString);
    MongoDatabase database = mongoClient.getDatabase(mongoDatabase);
    MongoCollection<Document> collection = database.getCollection(collectionName);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        String url = request.getPathInfo();
        String[] urlParts = url.split("/");

        try (Jedis jedis = jedisPool.getResource()) {
            String id = urlParts[1];
            String review = jedis.get(id);
            if (review == null) {
                response.setStatus(200);
                response.getWriter().println(gson.toJson(new Response(id, "0")));
            } else {
                response.setStatus(200);
                response.getWriter().println(review);
            }
        } catch (Exception e) {
            response.setStatus(400);
            response.getWriter().println(e);
        }
    }
}