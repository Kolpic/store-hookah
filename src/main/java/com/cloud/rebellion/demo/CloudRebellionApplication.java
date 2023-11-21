package com.cloud.rebellion.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Cloud Rebellion Project",
				version = "1.0.0",
				contact = @Contact (
						name = "Galin Petrov",
						email = "galincho112@gmail.com"
				)
		)
)
public class CloudRebellionApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudRebellionApplication.class, args);
	}
}
