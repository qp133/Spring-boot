package com.example.bookbackend;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootApplication
public class BookBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookBackendApplication.class, args);
	}

	@Bean
	public Faker faker() {
		return new Faker();
	}

	@Bean
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	@Bean
	public Slugify slugify() {
		return Slugify.builder()
				.lowerCase(true)
				.customReplacement("đ", "d")
				.customReplacement("Đ", "d")
				.build();
	}

}
