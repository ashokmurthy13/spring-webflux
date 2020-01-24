package com.toki.games.flight.task;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FlightAvailabilityConfiguration {

	@Bean
	public static PropertyPlaceholderConfigurer getProperty() {
		PropertyPlaceholderConfigurer propConfig = new PropertyPlaceholderConfigurer();
		propConfig.setLocation(new ClassPathResource("application.properties"));
		propConfig.setIgnoreUnresolvablePlaceholders(true);
		return propConfig;
	}
}
