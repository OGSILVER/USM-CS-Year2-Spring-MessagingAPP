package dev.skirtty.webmessaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebmessagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebmessagingApplication.class, args);
	}

}
