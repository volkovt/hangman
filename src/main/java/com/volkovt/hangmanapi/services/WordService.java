package com.volkovt.hangmanapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.volkovt.hangmanapi.dto.WordDTO;
import com.volkovt.hangmanapi.entities.Word;
import com.volkovt.hangmanapi.repositories.WordRepository;
import com.volkovt.hangmanapi.services.exceptions.ResourceNotFoundException;

@Service
public class WordService {

	@Autowired
	private WordRepository repository;
	
	@Transactional(readOnly=true)
	public WordDTO getRandomWord() {
		List<WordDTO> words = repository.getRandom();
		try {
			return words.stream().findFirst().get();
		} catch (NullPointerException n) {
			throw new ResourceNotFoundException("No word was registered");
		}
	}

	public List<WordDTO> getAllWords() {
		return repository.findAll(Sort.by("name"))
				.stream()
				.map(WordDTO::new)
				.collect(Collectors.toList());
	}
	
	public WordDTO insert(String name) {
		Optional<Word> existentWord = repository.findByName(name);
		if(existentWord.isPresent()) {
			throw new DuplicateKeyException(name);
		}
		
		Word word = Word.builder().name(name).build();
		return new WordDTO(repository.save(word));
	}

	public WordDTO getWord(Long id) {
		Optional<Word> word = repository.findById(id);
		return new WordDTO(word.orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
	}
}
