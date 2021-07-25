package dev.gaudnik.blog.controler

import dev.gaudnik.blog.model.BlogPost
import dev.gaudnik.blog.model.Review
import dev.gaudnik.blog.model.vo.RatingVO
import dev.gaudnik.blog.repository.BlogPostRepository
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest extends Specification {

    @Autowired
    MockMvc mvc

    @Autowired
    BlogPostRepository blogPostRepository

    @Shared
    String url = '/blogpost/'

    @Shared
    BlogPost blogPost

    @Shared
    Review review

    def setup() {
        def blogPostTitle = "title blog post test"
        def blogPostContent = "content blog post test"
        def reviewTitle = "title review"
        def reviewContent = "title content"
        def ratingVo = Mockito.mock(RatingVO)
        review = Review.builder().title(reviewTitle).content(reviewContent).rating(ratingVo).build()
        blogPost = BlogPost.builder()
                .title(blogPostTitle)
                .content(blogPostContent)
                .build()
        blogPost.addReview(review)
        blogPostRepository.addBlogPost(blogPost)
    }

    def "should create BlogPost"() {
        given:
        def reviewTitle = "title review test"
        def reviewContent = "content review test"
        def reviewRating = 3

        expect:
        mvc.perform(post(url + blogPost.getUuid() + "/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n \"title\": \"" + reviewTitle + "\",\n \"content\": \"" + reviewContent + "\", \n \"rating\": \"" + reviewRating + "\"\n }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("\$.uuid").hasJsonPath())
                .andExpect(jsonPath("\$.title").value(reviewTitle))
                .andExpect(jsonPath("\$.content").value(reviewContent))
                .andExpect(jsonPath("\$.rating").value(reviewRating))
    }

    def "should delete"() {
        expect:
        mvc.perform(delete(url + blogPost.getUuid() + "/review/" + review.getUuid()))
                .andExpect(status().isNoContent())
        mvc.perform(delete(url + blogPost.getUuid() + "/review/" + review.getUuid()))
                .andExpect(status().is(422))
    }

}
