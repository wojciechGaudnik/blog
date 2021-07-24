package dev.gaudnik.blog.config.exception;

import dev.gaudnik.blog.exception.NoSuchPostException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerPost {

	@ExceptionHandler({NoSuchPostException.class})
	public ResponseEntity<Object> handleNoSuchPostException(Exception exception, WebRequest request) {
		var errors = new HashMap<String, String>();
		errors.put("defaultMessage", exception.getMessage());
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.error("No Such Post")
				.errors(errors)
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
