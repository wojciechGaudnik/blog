package dev.gaudnik.blog.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

	@GetMapping(value ="/post", produces = "application/json")
	public String getAllPosts() {
		return "test";
	}
}
