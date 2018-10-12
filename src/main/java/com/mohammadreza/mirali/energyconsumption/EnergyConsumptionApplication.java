package com.mohammadreza.mirali.energyconsumption;

//import org.glassfish.jersey.media.multipart.MultiPartFeature;
//import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com/mohammadreza/mirali/energyconsumption/domain")
@ComponentScan("com.mohammadreza.mirali.energyconsumption")
public class EnergyConsumptionApplication{

	public EnergyConsumptionApplication() {
//		register(FractionProfileRest.class);
//		register(MultiPartFeature.class);

	}

	public static void main(String[] args) {
		SpringApplication.run(EnergyConsumptionApplication.class, args);
	}
}
