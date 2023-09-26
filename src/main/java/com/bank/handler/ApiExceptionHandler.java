package com.bank.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.dto.ResultDto;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(PropertyValueException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<ResultDto> badPropertyValue(PropertyValueException ex) {
		ResultDto result = new ResultDto("Operación incorrecta", ex.getLocalizedMessage());
		if (ex.getLocalizedMessage().startsWith("not-null property")) {
			result.setMessage(String.format("La propiedad '%s' no puede ser nula", ex.getPropertyName()));
		}
		if (ex.getLocalizedMessage().startsWith("not-blank property")) {
			result.setMessage(String.format("La propiedad '%s' debe tener texto", ex.getPropertyName()));
		}
		return ResponseEntity.badRequest().body(result);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ResultDto> badRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {
		ResultDto response = new ResultDto("KO");
		Map<String, Object> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		response.setErrors(errors);
		return ResponseEntity.badRequest().body(response);
	}
}
