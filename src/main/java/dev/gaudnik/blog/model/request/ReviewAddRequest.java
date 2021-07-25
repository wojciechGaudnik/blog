package dev.gaudnik.blog.model.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class ReviewAddRequest extends ContentRequest {

	@NotNull
	private Integer rating;
}
