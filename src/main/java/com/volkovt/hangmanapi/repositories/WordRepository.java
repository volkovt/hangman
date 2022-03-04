package com.volkovt.hangmanapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.volkovt.hangmanapi.dto.WordDTO;
import com.volkovt.hangmanapi.entities.Word;


public interface WordRepository extends JpaRepository<Word, Long> {
	
	@Query(value="SELECT new com.volkovt.hangmanapi.dto.WordDTO(w) "
			+ "FROM Word w "
			+ "ORDER BY RANDOM()")
	public List<WordDTO> getRandom();

	public Optional<Word> findByName(String name);
}
