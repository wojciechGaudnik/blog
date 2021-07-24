package dev.gaudnik.blog.controler;

import dev.gaudnik.blog.config.log.Logging;
import dev.gaudnik.blog.exception.NoSuchPostException;
import dev.gaudnik.blog.model.vo.RatingVOConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/post")
public class BlogController {

	private final RatingVOConfig ratingVOConfig;

	public BlogController(RatingVOConfig ratingVOConfig) {
		this.ratingVOConfig = ratingVOConfig;
	}

	@Logging
	@GetMapping(value = "/", produces = "application/json")
	public String getAllPosts() {
		return "test";
	}

	@Logging
	@GetMapping(value = "/{id}")
	public String get(@PathVariable String id) {
		throw new NoSuchPostException(id);
	}

}
