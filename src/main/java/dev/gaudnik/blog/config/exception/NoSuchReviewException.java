package dev.gaudnik.blog.config.exception;

import java.util.UUID;

public class NoSuchReviewException extends RuntimeException {

	public NoSuchReviewException(UUID uuid) {
		super(String.format("Review with UUID '%s' doesn't exist", uuid));
	}

}
