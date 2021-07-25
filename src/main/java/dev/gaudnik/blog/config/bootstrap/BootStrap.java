package dev.gaudnik.blog.config.bootstrap;

import dev.gaudnik.blog.model.BlogPost;
import dev.gaudnik.blog.model.Review;
import dev.gaudnik.blog.model.vo.RatingVO;
import dev.gaudnik.blog.model.vo.RatingVOConfig;
import dev.gaudnik.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

	@Value("${blog.bootstrap}")
	private String bootstrapCondition;

	private final BlogPostRepository blogPostRepository;

	private final RatingVOConfig ratingVOConfig;

	public BootStrap(BlogPostRepository blogPostRepository, RatingVOConfig ratingVOConfig) {
		this.blogPostRepository = blogPostRepository;
		this.ratingVOConfig = ratingVOConfig;
	}

	@Override
	public void run(String... args) {
		if (bootstrapCondition.equals("true")) {
			var rating1 = RatingVO.builder()
					.rating(2)
					.ratingVOConfig(ratingVOConfig).build();
			var rating2 = RatingVO.builder()
					.rating(3)
					.ratingVOConfig(ratingVOConfig).build();
			var rating3 = RatingVO.builder()
					.rating(4)
					.ratingVOConfig(ratingVOConfig).build();
			var review1 = Review.builder()
					.title("title review 1 to post 1")
					.content("content review 1 to post 1")
					.rating(rating1).build();
			var review2 = Review.builder()
					.title("title review 2 to post 1")
					.content("content review 2 to post 1")
					.rating(rating2).build();
			var review3 = Review.builder()
					.title("title review 3 to post 1")
					.content("content review 3 to post 1")
					.rating(rating3).build();
			var blogPost1 = BlogPost.builder()
					.title("title blog post 1")
					.content("content blog post 1").build();
			var blogPost2 = BlogPost.builder()
					.title("title blog post 2")
					.content("content blog post 2").build();
			var blogPost3 = BlogPost.builder()
					.title("title blog post 3")
					.content("content blog post 3").build();
			blogPost1.addReview(review1);
			blogPost1.addReview(review2);
			blogPost1.addReview(review3);
			blogPostRepository.addBlogPost(blogPost1);
			blogPostRepository.addBlogPost(blogPost2);
			blogPostRepository.addBlogPost(blogPost3);
		}
	}

}
