package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude={DataSourceAutoConfiguration.class})
public class SpringMVCApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMVCApplication.class, args);
	}

}
