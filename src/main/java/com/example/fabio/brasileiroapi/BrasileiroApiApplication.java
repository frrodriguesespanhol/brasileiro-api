package com.example.fabio.brasileiroapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@RestController
public class BrasileiroApiApplication {

	@Autowired
	private Environment env;
	
	@GetMapping("/")
	public String getAmbiente() {
		String ambienteAtual = "PADRÃO (nenhum)";
		
		if(env.getActiveProfiles().length > 0) {
			ambienteAtual = env.getActiveProfiles()[0];
		}
		
		String appName = env.getProperty("spring.application.name");
		
		return String.format("Ambiente: %s | App Name: %s", ambienteAtual, appName);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BrasileiroApiApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
