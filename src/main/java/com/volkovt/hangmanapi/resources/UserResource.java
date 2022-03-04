package com.volkovt.hangmanapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volkovt.hangmanapi.dto.UserDTO;
import com.volkovt.hangmanapi.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {
	@Autowired
	private UserService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
}
