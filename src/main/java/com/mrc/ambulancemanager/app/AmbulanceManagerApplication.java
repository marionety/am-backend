package com.mrc.ambulancemanager.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.mrc.ambulancemanager.app", "com.mrc.ambulancemanager.domain",
		"com.mrc.ambulancemanager.data" })
@EntityScan({ "com.mrc.ambulancemanager.data" })
@EnableJpaRepositories({ "com.mrc.ambulancemanager.data" })
public class AmbulanceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmbulanceManagerApplication.class, args);
	}

}
