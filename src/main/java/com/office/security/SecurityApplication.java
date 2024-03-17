package com.office.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.office.security.config.MyBeanNameGenerator;

@SpringBootApplication
@ComponentScan(nameGenerator = MyBeanNameGenerator.class)
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
