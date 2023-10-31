package com.example.obm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan(basePackages = {"com.mysite.sbb"})
@SpringBootApplication
public class ObmApplication {

	public static void main(String[] args) {

		SpringApplication.run(ObmApplication.class, args);
	}

}
