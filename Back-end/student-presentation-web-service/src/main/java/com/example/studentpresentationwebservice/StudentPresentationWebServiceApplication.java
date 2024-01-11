package com.example.studentpresentationwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class StudentPresentationWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentPresentationWebServiceApplication.class, args);
	}

}
