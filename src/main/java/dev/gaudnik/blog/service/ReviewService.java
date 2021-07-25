package dev.gaudnik.blog.service;

import dev.gaudnik.blog.model.Review;
import dev.gaudnik.blog.model.request.ReviewAddRequest;
import dev.gaudnik.blog.model.vo.RatingVOConfig;
import dev.gaudnik.blog.repository.BlogPostRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReviewService {

	private final BlogPostRepository blogPostRepository;

	private final RatingVOConfig ratingVOConfig;

	public ReviewService(BlogPostRepository blogPostRepository, RatingVOConfig ratingVOConfig) {
		this.blogPostRepository = blogPostRepository;
		this.ratingVOConfig = ratingVOConfig;
	}

	public Review addReview(UUID blogPostUuid, ReviewAddRequest reviewRequest) {
		var blogPost = blogPostRepository.getBlogPostByUUID(blogPostUuid);
		var review = Review.ofRequest(reviewRequest, ratingVOConfig);
		blogPost.addReview(review);
		return review;
	}
}
