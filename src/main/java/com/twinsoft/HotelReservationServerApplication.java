package com.twinsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.twinsoft.security.OAuth2AuthorizationServerConfig;

import nl.powerhouse.platform.auth.conf.AppOAuth2ServerConfiguration;
import nl.powerhouse.platform.auth.conf.AppWebSecurityConfiguration;


@SpringBootApplication
@Import({ OAuth2AuthorizationServerConfig.class })
public class HotelReservationServerApplication extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HotelReservationServerApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(HotelReservationServerApplication.class, args);
	}
}
