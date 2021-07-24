package dev.gaudnik.blog.controler;

import dev.gaudnik.blog.config.log.Logging;
import dev.gaudnik.blog.exception.NoSuchPostException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/post")
public class BlogController {

	@Logging
	@GetMapping(value = "/", produces = "application/json")

	public String getAllPosts() {
		return "test";
	}

	@Logging
	@GetMapping(value = "/{id}")
	public String get(@PathVariable("id") String id) {
		throw new NoSuchPostException(id);
	}
}
