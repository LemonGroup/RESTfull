package org.lemongroup.lemonstat.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

	System.out.println("RUN!");
    }
}
