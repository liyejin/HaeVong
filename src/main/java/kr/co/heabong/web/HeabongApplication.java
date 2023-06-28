package kr.co.heabong.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties(prefix = "spring.mail")
public class HeabongApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeabongApplication.class, args);
	}

}
