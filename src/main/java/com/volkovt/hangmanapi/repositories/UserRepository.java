package com.volkovt.hangmanapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.volkovt.hangmanapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
