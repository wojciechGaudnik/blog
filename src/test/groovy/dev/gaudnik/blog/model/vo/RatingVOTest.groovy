package dev.gaudnik.blog.model.vo

import dev.gaudnik.blog.config.exception.RatingOutOfRangeException
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class RatingVOTest extends Specification {

    @Shared
    RatingVOConfig mockRatingVOConfig;

    def setupSpec() {
        mockRatingVOConfig = Mock(RatingVOConfig)
        mockRatingVOConfig.getMinRating() >> 0
        mockRatingVOConfig.getMaxRating() >> 5
    }

    @Unroll
    def "create rating with correct range"(RatingVO ratingVO, Integer expect) {
        expect:
        ratingVO.getRating() == expect

        where:
        ratingVO                            | expect
        new RatingVO(1, mockRatingVOConfig) | 1
        new RatingVO(3, mockRatingVOConfig) | 3
        new RatingVO(5, mockRatingVOConfig) | 5
    }

    def "create rating with incorrect range min"() {
        when:
        new RatingVO(-1, mockRatingVOConfig)

        then:
        thrown(RatingOutOfRangeException)
    }

    def "create rating with incorrect range max"() {
        when:
        new RatingVO(6, mockRatingVOConfig)

        then:
        thrown(RatingOutOfRangeException)
    }

    def "new rating"() {
        given:
        def ratingVO = new RatingVO(4, mockRatingVOConfig)

        when:
        def newRatingVO1 = ratingVO.newRating(2)
        def newRatingVO2 = newRatingVO1.newRating(1)

        then:
        newRatingVO1.getRating() == 3

        and:
        newRatingVO2.getRating() == 2
    }

}
