package dev.gaudnik.blog.model;

import dev.gaudnik.blog.model.request.ReviewAddRequest;
import dev.gaudnik.blog.model.vo.RatingVO;
import dev.gaudnik.blog.model.vo.RatingVOConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class Review extends Content {

	private final RatingVO rating;

	@Builder
	public Review(@NonNull String title, @NonNull String content, @NonNull RatingVO rating) {
		super(title, content);
		this.rating = rating;
		this.uuid = UUID.randomUUID();
	}

	public static Review ofRequest(@NonNull ReviewAddRequest reviewAddRequest, RatingVOConfig ratingVOConfig) {
		var ratingVo = RatingVO.builder()
				.rating(reviewAddRequest.getRating())
				.ratingVOConfig(ratingVOConfig).build();
		return Review.builder()
				.title(reviewAddRequest.getTitle())
				.content(reviewAddRequest.getContent())
				.rating(ratingVo).build();
	}

	public Integer getRating() {
		return rating.getRating();
	}
}
