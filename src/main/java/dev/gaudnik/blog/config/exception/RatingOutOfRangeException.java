package dev.gaudnik.blog.config.exception;

public class RatingOutOfRangeException extends RuntimeException {

	public RatingOutOfRangeException(Integer min, Integer max, int rating) {
		super(String.format("Rating out of range, min: %s, max: %s, request rating: %s", min, max, rating));
	}

}
