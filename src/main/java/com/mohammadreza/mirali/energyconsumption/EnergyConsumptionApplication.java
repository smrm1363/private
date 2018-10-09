package com.mohammadreza.mirali.energyconsumption;

import entity.FractionService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("entity")
public class EnergyConsumptionApplication extends ResourceConfig{

	public EnergyConsumptionApplication() {
		register(FractionService.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(EnergyConsumptionApplication.class, args);
	}
}
