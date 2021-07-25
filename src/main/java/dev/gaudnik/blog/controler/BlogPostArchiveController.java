package dev.gaudnik.blog.controler;

import dev.gaudnik.blog.config.logging.Logging;
import dev.gaudnik.blog.model.dto.BlogPostDto;
import dev.gaudnik.blog.service.BlogPostService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/blogpostarchive")
public class BlogPostArchiveController {

	private final BlogPostService blogPostService;

	private final ModelMapper modelmapper;

	public BlogPostArchiveController(BlogPostService blogPostService, ModelMapper modelmapper) {
		this.blogPostService = blogPostService;
		this.modelmapper = modelmapper;
	}

	@Logging
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PostMapping(value = "/{uuid}")
	public BlogPostDto addBlogPost(@PathVariable UUID uuid) {
		return modelmapper.map(blogPostService.archiveBlogPost(uuid), BlogPostDto.class);
	}

}
