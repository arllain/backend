package com.arllain.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arllain.backend.services.S3Service;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner{

	@Autowired
	S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("C:\\00\\coffee.jpg");

	}
}
