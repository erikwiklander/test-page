package io.wiklandia.cpage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class CpageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpageApplication.class, args);
	}

	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

	@Bean
	public CommandLineRunner doIt(PersonRepository personRepository) {

		return args -> {

			for (int i = 0; i < 1233; i++) {
				Person p = Person.builder().firstName("Erik " + (i + 1)).lastName("Wiklander").build();
				personRepository.save(p);
			}
		};

	}

}
