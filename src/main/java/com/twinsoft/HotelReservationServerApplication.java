package com.twinsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class HotelReservationServerApplication extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HotelReservationServerApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(HotelReservationServerApplication.class, args);
	}
}
