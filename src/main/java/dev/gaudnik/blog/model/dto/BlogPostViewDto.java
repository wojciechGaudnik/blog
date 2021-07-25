package dev.gaudnik.blog.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Collection;
import java.util.UUID;

@Data
@ToString
public class BlogPostViewDto {

	private UUID uuid;

	private String title;

	private String content;

	private Integer averageRating;

	private Collection<ReviewDto> reviews;

}
