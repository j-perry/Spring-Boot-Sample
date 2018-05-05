package com.sample.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan
public class Main {
	
	/**
	 * run using 'spring-boot:run'
	 * or, even better, use 'install spring-boot:run'
	 * @param args
	 * @throws Exception
	 */
	public static void main(String [] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}

}