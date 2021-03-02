package com.brittodev.jobgetter;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.brittodev.jobgetter.Services.Create;

@SpringBootTest
class JobgetterApplicationTests {

	@Test
	void contextLoads() throws IOException {

		new Create().create("https://github.com/awesomeWM/awesome").toString();
		
		
	}

}
