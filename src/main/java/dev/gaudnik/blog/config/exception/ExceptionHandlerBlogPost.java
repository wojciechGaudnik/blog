package dev.gaudnik.blog.config.exception;

import dev.gaudnik.blog.exception.NoSuchBlogPostException;
import dev.gaudnik.blog.exception.RatingOutOfRangeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerBlogPost {

	@ExceptionHandler({NoSuchBlogPostException.class})
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


	@ExceptionHandler({RatingOutOfRangeException.class})
	public ResponseEntity<Object> handleRatingOutOfRangeException(Exception exception, WebRequest request) {
		var errors = new HashMap<String, String>();
		errors.put("defaultMessage", exception.getMessage());
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.error("Rating out of range")
				.errors(errors)
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleValidationException(Exception exception, WebRequest request) {
		var errors = new HashMap<String, String>();
		errors.put("defaultMessage", exception.getMessage());
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.error("Validation Error")
				.errors(errors)
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
