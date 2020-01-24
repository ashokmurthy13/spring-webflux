package com.toki.games.flight.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class FlightAvailabilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightAvailabilityApplication.class, args);
	}

}
