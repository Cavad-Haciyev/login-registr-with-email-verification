package com.example.emailverification;

import com.example.emailverification.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EmailVerificationApplication {


	public static void main(String[] args) {
		SpringApplication.run(EmailVerificationApplication.class, args);
	}
}
