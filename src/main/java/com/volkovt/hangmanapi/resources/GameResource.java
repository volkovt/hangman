package com.volkovt.hangmanapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volkovt.hangmanapi.dto.GameGuessDTO;
import com.volkovt.hangmanapi.services.GameService;

@RestController
@RequestMapping("/games")
public class GameResource {

	@Autowired
	private GameService service;
	
	@GetMapping("/{word}")
	public ResponseEntity<GameGuessDTO> guess(@PathVariable Character word) {
		return ResponseEntity.ok().body(service.guess(word.toString().toUpperCase()));
	}
	
	@DeleteMapping
	public ResponseEntity<Void> resetGame() {
		service.resetGame();
		return ResponseEntity.noContent().build();
	}
}
