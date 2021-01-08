package com.purchase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.ParseException;

@SpringBootApplication
@EnableScheduling
public class PurchaseApplication {


	public static void main(String[] args) throws ParseException {
//		SpringBootManager.run(CowboyingApplication.class, 8082, EnvironmentalEnum.DEV, args);1111111111111
		SpringApplication.run(PurchaseApplication.class, args);

	}



}
