package com.mercadonarest;

import com.github.benmanes.caffeine.cache.Caffeine;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@OpenAPIDefinition
public class MercadonaRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadonaRestApplication.class, args);
	}
}
