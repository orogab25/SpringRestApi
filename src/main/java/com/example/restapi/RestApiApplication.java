package com.example.restapi;

import com.example.restapi.models.Product;
import com.example.restapi.models.User;
import com.example.restapi.repositories.ProductsRepository;
import com.example.restapi.repositories.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductsRepository productsRepository, UsersRepository usersRepository){
		return args -> {
			productsRepository.save(new Product("product1","stuff",1L));
			usersRepository.save(new User("user1", new BCryptPasswordEncoder().encode("pass1"),"James Howard"));
		};
	}
}
