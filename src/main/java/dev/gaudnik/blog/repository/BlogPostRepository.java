package dev.gaudnik.blog.repository;

import dev.gaudnik.blog.exception.NoSuchBlogPostException;
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

	public BlogPost addBlogPost(BlogPost blogPost) {
		blogPost.setUuid(UUID.randomUUID());
		storage.put(blogPost.getUuid(), blogPost);
		return blogPost;
	}

	public BlogPost getBlogPostByUUID(UUID uuid) {
		return Optional.of(storage.get(uuid)).orElseThrow(() -> new NoSuchBlogPostException(uuid));
	}
}
