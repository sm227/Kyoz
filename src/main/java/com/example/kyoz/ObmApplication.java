package com.example.kyoz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EntityScan(basePackages = {"com.mysite.sbb"})
@SpringBootApplication
public class ObmApplication {

	public static void main(String[] args) {

		SpringApplication.run(ObmApplication.class, args);
	}

}
