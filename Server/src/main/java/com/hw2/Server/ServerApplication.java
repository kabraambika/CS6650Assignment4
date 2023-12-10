package com.hw2.Server;

import com.hw2.Server.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ServerApplication {

	public static void main(String[] args) {
		System.out.println("hey");SpringApplication.run(ServerApplication.class, args);
	}

}
