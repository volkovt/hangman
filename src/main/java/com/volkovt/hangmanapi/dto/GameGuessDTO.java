package com.volkovt.hangmanapi.dto;

import java.io.Serializable;

import com.volkovt.hangmanapi.entities.Game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameGuessDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String word;
	private String guess;
	private String misses;
	private String message;
	
	public GameGuessDTO(Game game) {
		this.word = game.getWord().getName();
		this.guess = game.getGuesses();
		this.misses = game.getMisses();
	}
}
