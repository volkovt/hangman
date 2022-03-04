package com.volkovt.hangmanapi.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.volkovt.hangmanapi.dto.exceptions.OAuthCustomError;
import com.volkovt.hangmanapi.dto.exceptions.StandardError;
import com.volkovt.hangmanapi.services.exceptions.ForbiddenException;
import com.volkovt.hangmanapi.services.exceptions.ResourceNotFoundException;
import com.volkovt.hangmanapi.services.exceptions.UnauthorizedException;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<OAuthCustomError> forbidden(ForbiddenException e, HttpServletRequest request) {
		OAuthCustomError err = new OAuthCustomError();
		err.setError("Forbidden");
		err.setErrorDescription(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<OAuthCustomError> unauthorized(UnauthorizedException e, HttpServletRequest request) {
		OAuthCustomError err = new OAuthCustomError();
		err.setError("Unauthorized");
		err.setErrorDescription(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
}
