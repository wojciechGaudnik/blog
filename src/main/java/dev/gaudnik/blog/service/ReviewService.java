package dev.gaudnik.blog.service;

import dev.gaudnik.blog.config.logging.Logging;
import dev.gaudnik.blog.model.BlogPost;
import dev.gaudnik.blog.model.Review;
import dev.gaudnik.blog.model.request.ReviewAddRequest;
import dev.gaudnik.blog.model.vo.RatingVOConfig;
import dev.gaudnik.blog.repository.BlogPostRepository;
import lombok.NonNull;
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

	@Logging
	public Review addReview(@NonNull UUID blogPostUuid, @NonNull ReviewAddRequest reviewRequest) {
		var blogPost = blogPostRepository.getBlogPostByUuid(blogPostUuid);
		var review = Review.ofRequest(reviewRequest, ratingVOConfig);
		blogPost.addReview(review);
		return review;
	}

	@Logging
	public void deleteReview(@NonNull UUID uuid, @NonNull UUID reviewUuid) {
		BlogPost blogPost = blogPostRepository.getBlogPostByUuid(uuid);
		blogPost.deleteReview(reviewUuid);
	}

}
