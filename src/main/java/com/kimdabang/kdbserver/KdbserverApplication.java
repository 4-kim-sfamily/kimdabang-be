package com.kimdabang.kdbserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class KdbserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(KdbserverApplication.class, args);
	}

}
