package dev.gaudnik.blog.model.vo;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class RatingVOConfig {

	@Value("${blog.rating.min}")
	private String minRating;

	@Value("${blog.rating.max}")
	private String maxRating;

	public Integer getMinRating() {
		return Integer.valueOf(minRating);
	}

	public Integer getMaxRating() {
		return Integer.valueOf(maxRating);
	}

}
