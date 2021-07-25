package dev.gaudnik.blog.controler;

import dev.gaudnik.blog.config.logging.Logging;
import dev.gaudnik.blog.model.dto.ReviewDto;
import dev.gaudnik.blog.model.request.ReviewAddRequest;
import dev.gaudnik.blog.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/blogpost/{uuid}/review")
public class ReviewController {

	private final ReviewService reviewService;

	private final ModelMapper modelmapper;

	public ReviewController(ReviewService reviewService, ModelMapper modelmapper) {
		this.reviewService = reviewService;
		this.modelmapper = modelmapper;
	}

	@Logging
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json")
	public ReviewDto addReview(@PathVariable UUID uuid, @Valid @RequestBody ReviewAddRequest review) {
		return modelmapper.map(reviewService.addReview(uuid, review), ReviewDto.class);
	}


	@Logging
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{reviewUuid}")
	public void deleteReview(@PathVariable UUID uuid, @PathVariable UUID reviewUuid) {
		reviewService.deleteReview(uuid, reviewUuid);
	}

}
