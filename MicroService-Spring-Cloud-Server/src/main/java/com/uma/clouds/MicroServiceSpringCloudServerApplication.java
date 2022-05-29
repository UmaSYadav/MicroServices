package com.uma.clouds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MicroServiceSpringCloudServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceSpringCloudServerApplication.class, args);
	}

}
