package id.ac.tazkia.zahir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZahirKafkaApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ZahirKafkaApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}
}
