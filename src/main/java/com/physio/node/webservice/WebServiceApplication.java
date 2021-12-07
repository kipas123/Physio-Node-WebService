package com.physio.node.webservice;

import net.bytebuddy.utility.RandomString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class WebServiceApplication {

	@EventListener(ApplicationReadyEvent.class)
	private void test(){
		String token = RandomString.make(20);
		System.out.println(token);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebServiceApplication.class, args);
	}

}
