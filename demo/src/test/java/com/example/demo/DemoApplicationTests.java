package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		String s =null;
		try {
			String s1 = s + "dsd";
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}


	}


}
