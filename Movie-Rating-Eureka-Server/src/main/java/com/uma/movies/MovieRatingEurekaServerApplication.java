package com.uma.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MovieRatingEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRatingEurekaServerApplication.class, args);
	}

}
