package com.store.pharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "PhaStore API", version = "1.0", description = "PhaStore Information"))
public class PhaAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhaAdminApplication.class, args);
	}

}

