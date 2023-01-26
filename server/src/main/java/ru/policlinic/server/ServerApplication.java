package ru.policlinic.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.policlinic.server.config.SwaggerConfig;
import ru.policlinic.server.repository.SpecializationsRepository;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Autowired
	private SpecializationsRepository specializationsRepository;

	@Override
	public void run(String... args) throws Exception {
		new SwaggerConfig().api();
	}
}
