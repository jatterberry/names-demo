package com.lab.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.lab.demo.repository")
public class NamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamesApplication.class, args);
	}
	
	public void run() {}
}
