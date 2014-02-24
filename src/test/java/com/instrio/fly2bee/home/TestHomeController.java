package com.instrio.fly2bee.home;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.instrio.fly2bee.config.web.AbstractWebApplicationTest;

public class TestHomeController extends AbstractWebApplicationTest {
	
	protected Logger log = LoggerFactory.getLogger(TestHomeController.class);
	
	@Test
	public void testVerify() throws Exception {
		log.debug("Junit TestHomeController > testHome");
		this.mockMvc.perform(get("/"))
			        .andExpect(status().isOk())
			        .andExpect(view().name("/home/home"));
	}
	
}

