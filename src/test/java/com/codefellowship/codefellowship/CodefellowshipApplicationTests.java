package com.codefellowship.codefellowship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
class CodefellowshipApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	private ApplicationUserController controller;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testUser() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void testLogin () throws Exception {

		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testHome () throws Exception {

		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
	}
	@Test
	public void testSignIn () throws Exception {

		this.mockMvc.perform(get("/signup")).andDo(print()).andExpect(status().isOk());
	}


}
