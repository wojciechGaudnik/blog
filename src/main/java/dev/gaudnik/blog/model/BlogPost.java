package dev.gaudnik.blog.model;

import dev.gaudnik.blog.config.exception.NoSuchReviewException;
import dev.gaudnik.blog.model.request.BlogPostAddRequest;
import dev.gaudnik.blog.model.vo.RatingVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
@ToString
public class BlogPost extends Content {

	private final Collection<Review> reviews = new ArrayList<>();

	@Builder
	public BlogPost(@NonNull String title, @NonNull String content) {
		super(title, content);
	}

	public static BlogPost ofRequest(@NonNull BlogPostAddRequest blogPostAddRequest) {
		return BlogPost.builder()
				.title(blogPostAddRequest.getTitle())
				.content(blogPostAddRequest.getContent()).build();
	}

	public void addReview(@NonNull Review review) {
		reviews.add(review);
	}

	public void deleteReview(@NonNull UUID uuid) {
		Review reviewToDelete = reviews.stream().filter(review -> review.getUuid().equals(uuid)).findFirst().orElseThrow(() -> new NoSuchReviewException(uuid));
		reviews.remove(reviewToDelete);
	}

	public Integer getAverageRating() {
		return Math.toIntExact(Math.round((double) reviews.stream()
				.map(Review::getRating)
				.map(RatingVO::getRating)
				.mapToInt(Integer::valueOf).sum() / reviews.size()));
	}


	public void setUuid(@NonNull UUID newUuid) {
		this.uuid = newUuid;
	}

}
