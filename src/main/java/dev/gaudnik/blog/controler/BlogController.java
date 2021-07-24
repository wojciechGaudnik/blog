package dev.gaudnik.blog.controler;

import dev.gaudnik.blog.config.log.Logging;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

	@Logging
	@GetMapping(value ="/post", produces = "application/json")
	public String getAllPosts() {
		return "test";
	}
}
