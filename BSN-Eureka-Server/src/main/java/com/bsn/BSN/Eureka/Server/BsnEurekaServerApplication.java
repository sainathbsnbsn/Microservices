package com.bsn.BSN.Eureka.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BsnEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BsnEurekaServerApplication.class, args);
	}

}
