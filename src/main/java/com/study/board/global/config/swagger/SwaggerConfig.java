package com.study.board.global.config.swagger;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@Configuration
@OpenAPIDefinition(
	info = @io.swagger.v3.oas.annotations.info.Info(
		title = "BASIC BOARD API",
		version = "v1"
	),
	servers = {
		@io.swagger.v3.oas.annotations.servers.Server(
			url = "http://localhost:8080",
			description = "local test"
		)
	}
)
public class SwaggerConfig {
}
