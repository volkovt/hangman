package com.volkovt.hangmanapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.volkovt.hangmanapi.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	Optional<Game> findFirstByOrderByIdDesc();
}
