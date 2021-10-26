package com.meli.projetointegradorgroup1.advisor;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.PersistenceException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RuntimeHandler {

	@ExceptionHandler(value = RuntimeException.class)
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.badRequest().body(bodyOfResponse);
	}

	@ExceptionHandler(value = ArithmeticException.class)
	protected ResponseEntity<Object> handleArithmetic(ArithmeticException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.badRequest().body(bodyOfResponse);
	}
	
	@ExceptionHandler(value = PersistenceException.class)
	protected ResponseEntity<Object> handleArithmetic(PersistenceException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.badRequest().body(bodyOfResponse);
	}
	
	@ExceptionHandler(value = ServiceException.class)
	protected ResponseEntity<Object> handleArithmetic(ServiceException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.badRequest().body(bodyOfResponse);
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExcep13tions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
