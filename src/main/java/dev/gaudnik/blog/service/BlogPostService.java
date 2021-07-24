package dev.gaudnik.blog.service;

import dev.gaudnik.blog.model.BlogPost;
import dev.gaudnik.blog.model.request.BlogPostAddRequest;
import dev.gaudnik.blog.model.vo.RatingVOConfig;
import dev.gaudnik.blog.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BlogPostService {

	private final RatingVOConfig ratingVOConfig;
	private final BlogPostRepository blogPostRepository;

	public BlogPostService(RatingVOConfig ratingVOConfig, BlogPostRepository blogPostRepository) {
		this.ratingVOConfig = ratingVOConfig;
		this.blogPostRepository = blogPostRepository;
	}

	public Collection<BlogPost> getAllBlogPosts() {
		return blogPostRepository.getAllBlogPosts();
	}

	public BlogPost addBlogPost(BlogPostAddRequest blogPostAddRequest) {
		return blogPostRepository.addBlogPost(BlogPost.ofRequest(blogPostAddRequest));
	}
}
