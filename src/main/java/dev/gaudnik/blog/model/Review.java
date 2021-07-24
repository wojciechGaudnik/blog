package dev.gaudnik.blog.model;

import dev.gaudnik.blog.model.vo.RatingVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@Getter
public class Review extends Content {

	private final RatingVO rating;

	@Builder
	public Review(@NonNull String title, @NonNull String content, @NonNull RatingVO rating) {
		super(title, content);
		this.rating = rating;
	}
}
