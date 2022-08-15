package com.github.omaru.saml2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(value = SecurityConfigTest.class)
class Saml2SampleApplicationTests {

	@Test
	void contextLoads() {
	}

}
