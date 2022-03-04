package com.volkovt.hangmanapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.volkovt.hangmanapi.dto.UserDTO;
import com.volkovt.hangmanapi.entities.User;
import com.volkovt.hangmanapi.repositories.UserRepository;
import com.volkovt.hangmanapi.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Autowired
	private AuthService authService;
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Transactional(readOnly=true)
	public UserDTO findById(Long id) {
		authService.validateSelfOrAdmin(id);
		return repository.findById(id).map(UserDTO::new).orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if(user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		logger.info("User found: " + username);
		
		return user;
	}
}
