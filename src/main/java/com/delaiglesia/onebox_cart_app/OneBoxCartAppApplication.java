package com.delaiglesia.onebox_cart_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OneBoxCartAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneBoxCartAppApplication.class, args);
	}

}
