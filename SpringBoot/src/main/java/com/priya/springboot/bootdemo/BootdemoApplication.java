package com.priya.springboot.bootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages="com.priya.springboot.bootdemo.repository")
@SpringBootApplication
public class BootdemoApplication {

	public static void main(String[] args) {
	SpringApplication.run(BootdemoApplication.class, args);
	
	
	}
}
