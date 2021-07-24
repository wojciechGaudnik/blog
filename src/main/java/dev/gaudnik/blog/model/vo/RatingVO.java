package dev.gaudnik.blog.model.vo;

import dev.gaudnik.blog.exception.RatingOutOfRangeException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class RatingVO {

	private Integer rating = 1;

	private RatingVOConfig ratingVOConfig;

	@Builder
	public RatingVO(@NonNull Integer rating, @NonNull RatingVOConfig ratingVOConfig) {
		this.ratingVOConfig = ratingVOConfig;
		this.rating = rating;
		validateRating(rating);
	}

	public RatingVO newRating(@NonNull Integer rating) {
		return new RatingVO(Math.toIntExact(Math.round(((this.rating + rating) / 2.0))), ratingVOConfig);
	}

	private void validateRating(@NonNull Integer rating) {
		if (rating < ratingVOConfig.getMinRating() || rating > ratingVOConfig.getMaxRating()) {
			throw new RatingOutOfRangeException(ratingVOConfig.getMinRating(), ratingVOConfig.getMaxRating(), rating);
		}
	}

}
