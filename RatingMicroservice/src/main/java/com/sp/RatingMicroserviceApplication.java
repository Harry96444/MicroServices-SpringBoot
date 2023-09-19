package com.sp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaServer
public class RatingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingMicroserviceApplication.class, args);
	}

}
