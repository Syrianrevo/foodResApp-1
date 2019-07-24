package com.syrianrevo.foodApp;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.syrianrevo.foodApp.kafkaConfig.Constants;
import com.syrianrevo.foodApp.model.JsonProducer;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class FoodResApp1Application {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		
		new JsonProducer(Constants.JSON_FILE);
		
		
		SpringApplication.run(FoodResApp1Application.class, args);
	}

}
