package com.volkovt.hangmanapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.volkovt.hangmanapi.entities.User;
import com.volkovt.hangmanapi.repositories.UserRepository;
import com.volkovt.hangmanapi.services.exceptions.ForbiddenException;
import com.volkovt.hangmanapi.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly=true)
	public User authenticated() {
		try {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(username);
		} catch (Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}
	
	public void validateSelfOrAdmin(Long userId) {
		User user = authenticated();
		if(!user.getId().equals(userId) && !user.hasRole("ROLE_ADMIN")) {
			throw new ForbiddenException("Access denied");
		}
	}
}
