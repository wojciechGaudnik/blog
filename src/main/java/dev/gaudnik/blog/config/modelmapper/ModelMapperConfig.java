package dev.gaudnik.blog.config.modelmapper;

import dev.gaudnik.blog.model.Review;
import dev.gaudnik.blog.model.dto.ReviewDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {

	@Bean
	public ModelMapper getModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper
				.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldMatchingEnabled(true)
				.setSkipNullEnabled(true);

		addReviewToDtoTypeMap(modelMapper);
		return modelMapper;
	}

	private void addReviewToDtoTypeMap(ModelMapper modelMapper) {
		modelMapper
				.createTypeMap(Review.class, ReviewDto.class)
				.addMapping(review -> review.getRating().getRating(), ReviewDto::setRating);
	}

}
