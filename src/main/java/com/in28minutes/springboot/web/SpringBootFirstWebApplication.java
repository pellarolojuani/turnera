package com.in28minutes.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import ar.uba.fi.mongo.repository.UserRepository;

@SpringBootApplication(scanBasePackages={"com.in28minutes.springboot.web", "ar.uba.fi"})
@ComponentScan({"com.in28minutes.springboot.web", "ar.uba.fi"})
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class SpringBootFirstWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstWebApplication.class, args);
	}
}
