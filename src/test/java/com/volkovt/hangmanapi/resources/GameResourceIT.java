package com.volkovt.hangmanapi.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.volkovt.hangmanapi.tests.TokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GameResourceIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	private Character existingGuess;
	private Character nonExistingGuess;
	
	private String username;
	private String password;
	
	@BeforeEach
	void setUp() throws Exception {
		existingGuess = 'a';
		nonExistingGuess = 'b';
		
		username = "admin@gmail.com";
		password = "123456";
	}
	
	@Test
	public void guessShouldReturnMessageWhenRepeatedGuess() throws Exception {
		ResultActions result = mockMvc.perform(get("/games/" + nonExistingGuess).accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.message").doesNotExist());
	}
	
	@Test
	public void guessShouldReturnNoMessageWhenNewGuess() throws Exception {
		mockMvc.perform(get("/games/" + existingGuess).accept(MediaType.APPLICATION_JSON));
		ResultActions result = mockMvc.perform(get("/games/" + existingGuess).accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.message").value("You already play this word!!"));
	}
	
	@Test
	public void resetShouldReturnNoContent() throws Exception {
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
		
		ResultActions result = 
				mockMvc.perform(delete("/games")
					.header("Authorization", "Bearer " + accessToken)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
}
