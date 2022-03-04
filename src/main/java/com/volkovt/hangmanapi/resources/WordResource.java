package com.volkovt.hangmanapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.volkovt.hangmanapi.dto.WordDTO;
import com.volkovt.hangmanapi.services.WordService;
import java.net.URI;

@RestController
@RequestMapping("/words")
public class WordResource {

	@Autowired
	private WordService service;
	
	@GetMapping
	public ResponseEntity<List<WordDTO>> getAllWords() {
		return ResponseEntity.ok().body(service.getAllWords());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WordDTO> getWord(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.getWord(id));
	}
	
	@PostMapping
	public ResponseEntity<WordDTO> insert(@RequestParam(value = "name", defaultValue = "") String name) {
		WordDTO wordDTO = service.insert(name);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(wordDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(wordDTO);
	}
}
