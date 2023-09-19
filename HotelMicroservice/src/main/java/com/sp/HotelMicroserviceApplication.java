package com.sp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HotelMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelMicroserviceApplication.class, args);
	}

}
