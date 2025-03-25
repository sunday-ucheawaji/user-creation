package com.sundayuche.usercreation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UsercreationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsercreationApplication.class, args);
	}

}
