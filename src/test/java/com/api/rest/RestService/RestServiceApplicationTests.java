package com.api.rest.RestService;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestServiceApplicationTests {
	private final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);
	@Test
	void contextLoads() {
		
		log.info("prueba de test by Spring");
	}

}
