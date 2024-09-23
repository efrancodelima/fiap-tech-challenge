package br.com.fiap.tech_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

@SpringBootApplication
public class TechChallengeApplication {

	public static void main(String[] args) {
		var application = new SpringApplication(TechChallengeApplication.class);
		application.setApplicationStartup(new BufferingApplicationStartup(1024));
		var context = application.run(args);

		PopularBD.popular(context);
	}

}
