package dev.gaudnik.blog.repository;

import dev.gaudnik.blog.config.exception.NoSuchBlogPostException;
import dev.gaudnik.blog.config.logging.Logging;
import dev.gaudnik.blog.model.BlogPost;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Component
public class BlogPostRepository {

	private final HashMap<UUID, BlogPost> storage = new HashMap<>();

	public Collection<BlogPost> getAllBlogPosts() {
		return new ArrayList<>(storage.values());
	}

	@Logging
	public BlogPost addBlogPost(BlogPost blogPost) {
		blogPost.setUuid(UUID.randomUUID());
		storage.put(blogPost.getUuid(), blogPost);
		return blogPost;
	}

	@Logging
	public BlogPost getBlogPostByUuid(UUID uuid) {
		return Optional.ofNullable(storage.get(uuid)).orElseThrow(() -> new NoSuchBlogPostException(uuid));
	}

	@Logging
	public BlogPost removeBlogPost(UUID uuid) {
		return Optional.ofNullable(storage.remove(uuid)).orElseThrow(() -> new NoSuchBlogPostException(uuid));
	}

}
