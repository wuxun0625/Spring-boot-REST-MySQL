package com.oracle.microsvctk.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTests {

	@Test
	public void contextLoads() {
		HelloController helloController = new HelloController();
		System.out.println(helloController.greeting());
	}

}
