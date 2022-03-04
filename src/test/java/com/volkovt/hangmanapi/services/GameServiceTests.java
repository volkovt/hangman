package com.volkovt.hangmanapi.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.volkovt.hangmanapi.dto.GameGuessDTO;
import com.volkovt.hangmanapi.dto.WordDTO;
import com.volkovt.hangmanapi.entities.Game;
import com.volkovt.hangmanapi.entities.Word;
import com.volkovt.hangmanapi.repositories.GameRepository;

@ExtendWith(SpringExtension.class)
public class GameServiceTests {

	@InjectMocks
	private GameService service;
	
	@Mock
	private GameRepository repository;
	
	@Mock
	private WordService serviceWord;
	
	private Long id;
	private String fullWord;
	private String replacedWord;
	
	@BeforeEach
	void setUp() throws Exception {
		fullWord = "DELL";
		replacedWord = "____";
		id = 1L;
	}

	@Test
	public void guessShouldReturnFilledWordWhenRightGuess() {
		String guess = "D";
		
		Game game = Game.builder()
				.id(id)
				.guesses("D")
				.misses("")
				.word(Word.builder().id(id).name(fullWord).build())
				.build();
		
		Mockito.when(serviceWord.getRandomWord()).thenReturn(WordDTO.builder().id(id).word(fullWord).build());
		Mockito.when(repository.findFirstByOrderByIdDesc()).thenReturn(Optional.empty());
		Mockito.when(repository.save(Mockito.any())).thenReturn(game);
		
		GameGuessDTO result = service.guess(guess);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getWord(), "D___");
		Assertions.assertEquals(result.getGuess(), guess);
	}
	
	@Test
	public void guessShouldReturnMessageOfRepeatWhenRepeatedGuess() {
		String guess = "D";
		Game game = Game.builder()
				.id(id)
				.guesses("D E")
				.misses("")
				.word(Word.builder().id(id).name(fullWord).build())
				.build();
		Mockito.when(repository.findFirstByOrderByIdDesc()).thenReturn(Optional.of(game));
		
		GameGuessDTO result = service.guess(guess);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getMessage(), "You already play this word!!");
		Assertions.assertEquals(result.getGuess(), guess);
	}
	
	@Test
	public void guessShouldReturnMessageOfSavedWhenAllRightGuesses() {
		String guess = "L";
		Game game = Game.builder()
				.id(id)
				.guesses("D E")
				.misses("")
				.word(Word.builder().id(id).name(fullWord).build())
				.build();
		Mockito.when(repository.findFirstByOrderByIdDesc()).thenReturn(Optional.of(game));
		
		GameGuessDTO result = service.guess(guess);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getMessage(), "YOU'RE SAVED!!");
		Assertions.assertEquals(result.getGuess(), guess);
	}
	
	@Test
	public void guessShouldReturnMessageOfHangeddWhenAllWrongGuesses() {
		String guess = "H";
		
		Game game = Game.builder()
				.id(id)
				.guesses("")
				.misses("A,B,C,F,G")
				.word(Word.builder().id(id).name(replacedWord).build())
				.build();
		Mockito.when(repository.findFirstByOrderByIdDesc()).thenReturn(Optional.of(game));
		
		GameGuessDTO result = service.guess(guess);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getMessage(), "YOU WERE HANGED!!");
		Assertions.assertEquals(result.getGuess(), guess);
	}
}
