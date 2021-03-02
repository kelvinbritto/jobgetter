package com.brittodev.jobgetter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JobgetterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobgetterApplication.class, args);
	}

}
