package dev.gaudnik.blog.exception;

public class NoSuchPostException extends RuntimeException {
	public NoSuchPostException(String id) {
		super(String.format("Post with id '%s' doesn't exist", id));
	}
}
