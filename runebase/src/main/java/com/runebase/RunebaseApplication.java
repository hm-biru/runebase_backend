package com.runebase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runebase.database.User;
import com.runebase.database.UserRepository;

@SpringBootApplication
@RestController
public class RunebaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunebaseApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
				User user = new User(name);
				userRepository.save(user);
			});
			userRepository.findAll().forEach(System.out::println);
		};
	}
}
