package com.volkovt.hangmanapi.services;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.volkovt.hangmanapi.dto.GameGuessDTO;
import com.volkovt.hangmanapi.dto.WordDTO;
import com.volkovt.hangmanapi.entities.Game;
import com.volkovt.hangmanapi.entities.Word;
import com.volkovt.hangmanapi.repositories.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository repository;
	
	@Autowired
	private WordService serviceWord;

	@Transactional(readOnly=false)
	public GameGuessDTO guess(String wordGuess) {
		Optional<Game> result = repository.findFirstByOrderByIdDesc();
		String guess = wordGuess;
	
		if(result.isEmpty()) return createNewGame(guess);
		
		Game game = result.get();
		guess += " ".concat(game.getGuesses());	
		String word = result.get().getWord().getName().replaceAll("[^" + guess + "]", "_");
		
		// In an alternative i used BusinessRuleException
		if(game.getGuesses().contains(wordGuess)) {
			GameGuessDTO gameGuessDTO = new GameGuessDTO();
			gameGuessDTO.setGuess(wordGuess);
			gameGuessDTO.setWord(word);
			gameGuessDTO.setMisses(game.getMisses());
			gameGuessDTO.setMessage("You already play this word!!");
			return gameGuessDTO;
		} 

		if(!word.contains("_")) {
			resetGame();
			return GameGuessDTO.builder().word(word).guess(wordGuess).misses(game.getMisses()).message("YOU'RE SAVED!!").build();
		}
		
		String misses = game.getMisses() == null ? "" : game.getMisses();
		if(!game.getWord().getName().contains(wordGuess)) {
			misses = wordGuess.concat(misses).replace(",", "");
			char[] c = misses.toCharArray(); 
			Arrays.sort(c); 
			misses = CharBuffer.wrap(c).chars()
					  .mapToObj(intValue -> String.valueOf((char) intValue))
					  .collect(Collectors.joining(","));
			if(c.length == 6) {
				resetGame();
				return GameGuessDTO.builder().word(word).guess(wordGuess).misses(misses).message("YOU WERE HANGED!!").build();
			}
		}
		
		game.setGuesses(guess);
		game.setMisses(misses);
		
		repository.save(game);
		return GameGuessDTO.builder().word(word).guess(wordGuess).misses(misses).build();
	}
	
	@Transactional(readOnly=false)
	private GameGuessDTO createNewGame(String guess) {
		Game game = new Game();
		WordDTO wordDTO = serviceWord.getRandomWord();
		String word = wordDTO.getWord().replaceAll("[^" + guess + "]", "_");
		game.setWord(Word.builder()
					.id(wordDTO.getId())
					.name(wordDTO.getWord()).build());
		game.setGuesses(guess);
		if(!game.getWord().getName().contains(guess)) {
			game.setMisses(guess);
		}
		game = repository.save(game);
		GameGuessDTO result = new GameGuessDTO(game);
		result.setWord(word);
		return result;
	}
	
	@Transactional(readOnly=false)
	public void resetGame() {
		repository.deleteAll();
	}
}
