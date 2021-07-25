package dev.gaudnik.blog.service;

import dev.gaudnik.blog.config.log.Logging;
import dev.gaudnik.blog.model.BlogPost;
import dev.gaudnik.blog.model.request.BlogPostAddRequest;
import dev.gaudnik.blog.repository.BlogPostArchiveRepository;
import dev.gaudnik.blog.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class BlogPostService {

	private final BlogPostRepository blogPostRepository;
	private final BlogPostArchiveRepository blogPostArchiveRepository;

	public BlogPostService(BlogPostRepository blogPostRepository, BlogPostArchiveRepository blogPostArchiveRepository) {
		this.blogPostRepository = blogPostRepository;
		this.blogPostArchiveRepository = blogPostArchiveRepository;
	}

	@Logging
	public Collection<BlogPost> getAllBlogPosts() {
		return blogPostRepository.getAllBlogPosts();
	}

	@Logging
	public BlogPost addBlogPost(BlogPostAddRequest blogPostAddRequest) {
		return blogPostRepository.addBlogPost(BlogPost.ofRequest(blogPostAddRequest));
	}

	@Logging
	public BlogPost getByUuid(UUID uuid) {
		return blogPostRepository.getBlogPostByUUID(uuid);
	}

	@Logging
	public BlogPost archiveBlogPost(UUID uuid) {
		return blogPostArchiveRepository.archiveBlogPost( blogPostRepository.removeBlogPost(uuid));
	}
}
