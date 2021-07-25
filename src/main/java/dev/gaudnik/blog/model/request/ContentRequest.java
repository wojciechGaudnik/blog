package dev.gaudnik.blog.model.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
public class ContentRequest {
	@NotBlank
	@Size(min = 3, max = 50, message = "title length out of range")
	private String title;

	@NotBlank
	@Size(min = 3, max = 5000, message = "content length out of range")
	private String content;

}
