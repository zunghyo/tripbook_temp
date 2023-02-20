package tripbook.tripbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TripbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripbookApplication.class, args);
	}

}
