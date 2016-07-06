package com.lab.demo.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lab.demo.controllers.GenderRestClient;
import com.lab.demo.model.GenderItem;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GenderRestClient.class)
@WebAppConfiguration

public class GenderRestClientTests {

	@Test
	public void testGetGenderInfo() {
		
		GenderRestClient genderClient = new GenderRestClient();
		GenderItem item = genderClient.getGenderInfo("Sue");
		assert(item != null);
	}

}
