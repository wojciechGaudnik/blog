package dev.gaudnik.blog.model;

import dev.gaudnik.blog.model.vo.RatingVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@ToString
public class BlogPost extends Content {

	private final Collection<Review> reviews = new ArrayList<>();

	@Builder
	public BlogPost(@NonNull String title, @NonNull String content) {
		super(title, content);
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

	public void addReview(@NonNull Review review) {
		reviews.add(review);
	}

	public Integer getAverageRating() {
		return Math.toIntExact(Math.round((double) reviews.stream()
				.map(Review::getRating)
				.map(RatingVO::getRating)
				.mapToInt(Integer::valueOf).sum() / reviews.size()));
	}
}
