package com.volkovt.hangmanapi.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.volkovt.hangmanapi.dto.exceptions.BusinessError;
import com.volkovt.hangmanapi.services.exceptions.BusinessRuleException;

@RestControllerAdvice
public class BusinessExceptionHandler {

	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<BusinessError> entityNotFound(BusinessRuleException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		BusinessError err = new BusinessError();
		err.setStatus(status.value());
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
}
