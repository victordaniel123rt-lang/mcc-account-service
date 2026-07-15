package com.vdgarcia.mcc_account_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MccAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MccAccountServiceApplication.class, args);
	}

}
