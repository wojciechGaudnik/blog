package dev.gaudnik.blog.config.exception;

import java.util.UUID;

public class NoSuchBlogPostException extends RuntimeException {
	public NoSuchBlogPostException(UUID uuid) {
		super(String.format("Blog Post with UUID '%s' doesn't exist", uuid));
	}
}
