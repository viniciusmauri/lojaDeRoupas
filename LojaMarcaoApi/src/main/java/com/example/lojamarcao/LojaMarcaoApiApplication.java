package com.example.lojamarcao;

import com.example.lojamarcao.config.property.LojaMarcaoApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(LojaMarcaoApiProperty.class)
public class LojaMarcaoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaMarcaoApiApplication.class, args);
	}

}
