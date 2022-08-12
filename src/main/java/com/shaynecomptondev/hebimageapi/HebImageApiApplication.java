package com.shaynecomptondev.hebimageapi;

import com.shaynecomptondev.hebimageapi.services.GoogleImageAnalyzer;
import com.shaynecomptondev.hebimageapi.services.ImageAnalyzer;
import com.shaynecomptondev.hebimageapi.services.ImageServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HebImageApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HebImageApiApplication.class, args);
	}

	//todo move beans to configuration file
	@Bean
	public ImageServiceImpl getImageService() {
		return new ImageServiceImpl();
	}
	@Bean
	public ImageAnalyzer getImageAnalyzer() {
		return new GoogleImageAnalyzer();
	}
}
