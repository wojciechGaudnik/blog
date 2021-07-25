package dev.gaudnik.blog.repository;

import dev.gaudnik.blog.model.BlogPost;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class BlogPostArchiveRepository {

	private final HashMap<UUID, BlogPost> archive = new HashMap<>();

	public BlogPost archiveBlogPost(BlogPost blogPostToArchive) {
		archive.put(blogPostToArchive.getUuid(), blogPostToArchive);
		return archive.get(blogPostToArchive.getUuid());
	}
}
