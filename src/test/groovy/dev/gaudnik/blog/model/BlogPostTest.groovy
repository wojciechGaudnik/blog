package dev.gaudnik.blog.model

import dev.gaudnik.blog.model.vo.RatingVO
import spock.lang.Specification

class BlogPostTest extends Specification {

    def "should return correct average rating"() {
        given:
        def mockReview1 = Mock(Review)
        def mockReview2 = Mock(Review)
        def mockReview3 = Mock(Review)
        def mockRatingVO1 = Mock(RatingVO)
        def mockRatingVO2 = Mock(RatingVO)
        def mockRatingVO3 = Mock(RatingVO)
        mockRatingVO1.getRating() >> 1
        mockRatingVO2.getRating() >> 2
        mockRatingVO3.getRating() >> 4
        mockReview1.getRating() >> mockRatingVO1
        mockReview2.getRating() >> mockRatingVO2
        mockReview3.getRating() >> mockRatingVO3

        when:
        def post = BlogPost.builder().title("title test").content("content test").build()
        post.addReview(mockReview1)
        post.addReview(mockReview2)

        then:
        post.getAverageRating() == 2

    }
}
