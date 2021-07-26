package dev.gaudnik.blog.model

import spock.lang.Specification

class BlogPostTest extends Specification {

    def "should return correct average rating"() {
        given:
        def mockReview1 = Mock(Review)
        def mockReview2 = Mock(Review)
        def mockReview3 = Mock(Review)
        mockReview1.getRating() >> 1
        mockReview2.getRating() >> 2
        mockReview3.getRating() >> 4

        when:
        def post = BlogPost.builder()
                .title("title test")
                .content("content test").build()
        post.addReview(mockReview1)
        post.addReview(mockReview2)

        then:
        post.getAverageRating() == 2
    }

}
