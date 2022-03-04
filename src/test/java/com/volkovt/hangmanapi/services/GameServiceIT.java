package com.volkovt.hangmanapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.volkovt.hangmanapi.repositories.GameRepository;

@SpringBootTest
@Transactional
public class GameServiceIT {

	@Autowired
	private GameService service;
	
	@Autowired
	private GameRepository repository;
	
	@Test
	public void resetGameShouldDeleteAll() {
		service.guess("a");
		Assertions.assertEquals(1L, repository.count());
		service.resetGame();
		Assertions.assertEquals(0L, repository.count());
	}
}
