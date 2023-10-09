package capgemini.challenge.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class CapgeminiChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapgeminiChallengeApplication.class, args);
	}

}
