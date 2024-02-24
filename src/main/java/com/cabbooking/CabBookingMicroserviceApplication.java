package com.cabbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.cabbooking")
public class CabBookingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabBookingMicroserviceApplication.class, args);
	}
	
	
}
