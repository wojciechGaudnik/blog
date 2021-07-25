package dev.gaudnik.blog.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class ReviewDto {

	private UUID uuid;

	private String title;

	private String content;

	private Integer rating;

}
