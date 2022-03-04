package com.volkovt.hangmanapi.dto;

import java.io.Serializable;

import com.volkovt.hangmanapi.entities.Word;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class WordDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String word;
	
	public WordDTO(Word word) {
		this.id = word.getId();
		this.word = word.getName();
	}
}
