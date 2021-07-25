package dev.gaudnik.blog.controler;

import dev.gaudnik.blog.config.log.Logging;
import dev.gaudnik.blog.model.dto.BlogPostDto;
import dev.gaudnik.blog.model.dto.BlogPostViewDto;
import dev.gaudnik.blog.model.request.BlogPostAddRequest;
import dev.gaudnik.blog.service.BlogPostService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/blogpost")
public class BlogPostController {

	private final BlogPostService blogPostService;

	private final ModelMapper modelmapper;

	public BlogPostController(BlogPostService blogPostService, ModelMapper modelmapper) {
		this.blogPostService = blogPostService;
		this.modelmapper = modelmapper;
	}

	@Logging
	@GetMapping(value = "/{uuid}", produces = "application/json")
	public BlogPostViewDto getByUUID(@PathVariable UUID uuid) {
		return modelmapper.map(blogPostService.getByUuid(uuid), BlogPostViewDto.class);
	}

	@Logging
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json")
	public BlogPostDto addBlogPost(@Valid @RequestBody BlogPostAddRequest blogPost) {
		return modelmapper.map(blogPostService.addBlogPost(blogPost), BlogPostDto.class);
	}

	@Logging
	@GetMapping(produces = "application/json")
	public Collection<BlogPostViewDto> getAllBlogPosts() {
		return blogPostService.getAllBlogPosts().stream().map(blogPost -> modelmapper.map(blogPost, BlogPostViewDto.class)).collect(Collectors.toList());
	}

}
