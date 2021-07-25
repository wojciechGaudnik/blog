package dev.gaudnik.blog.controler

import com.fasterxml.jackson.databind.ObjectMapper
import dev.gaudnik.blog.config.BlogPostControllerConfiguration
import dev.gaudnik.blog.model.BlogPost
import dev.gaudnik.blog.model.Review
import dev.gaudnik.blog.model.request.BlogPostAddRequest
import dev.gaudnik.blog.model.vo.RatingVO
import dev.gaudnik.blog.model.vo.RatingVOConfig
import dev.gaudnik.blog.service.BlogPostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import static org.hamcrest.Matchers.hasSize
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class BlogPostControllerTest extends Specification implements BlogPostControllerConfiguration{

    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper mapper

    @Autowired
    RatingVOConfig ratingVOConfig

    @MockBean
    BlogPostService blogPostService

    @Shared
    BlogPost blogPost1

    @Shared
    BlogPost blogPost2


    def setup() {
        def ratingVo1 = RatingVO.builder()
                .rating(rating1)
                .ratingVOConfig(ratingVOConfig).build()
        def ratingVo2 = RatingVO.builder()
                .rating(rating2)
                .ratingVOConfig(ratingVOConfig).build()
        def review1 = Review.builder()
                .title(reviewTitle1)
                .content(reviewContent1)
                .rating(ratingVo1).build()
        def review2 = Review.builder()
                .title(reviewTitle2)
                .content(reviewContent2)
                .rating(ratingVo2).build()
        blogPost1 = BlogPost.builder()
                .title(blogPostTitle1)
                .content(blogPostContent1).build()
        blogPost2 = BlogPost.builder()
                .title(blogPostTitle2)
                .content(blogPostContent2).build()
        blogPost1.addReview(review1)
        blogPost1.addReview(review2)
    }

    def "should create BlogPost"() {
        given:
        when(blogPostService.addBlogPost(any(BlogPostAddRequest.class))).thenReturn(BlogPost.builder()
                .title(blogPostTitle1)
                .content(blogPostContent1).build())


        expect:
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n \"title\": \"" + blogPostTitle1 + "\",\n \"content\": \"" + blogPostContent1 + "\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("\$.title").value(blogPostTitle1))
                .andExpect(jsonPath("\$.content").value(blogPostContent1))
                .andExpect(jsonPath("\$.uuid").hasJsonPath())
    }

    def "should return all BlogPosts"() {
        given:
        when(blogPostService.getAllBlogPosts()).thenReturn(Arrays.asList(blogPost1, blogPost2))

        expect:
        mvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(jsonPath("\$", hasSize(2)))
                .andExpect(jsonPath("\$[0].uuid").hasJsonPath())
                .andExpect(jsonPath("\$[0].title").value(blogPostTitle1))
                .andExpect(jsonPath("\$[0].content").value(blogPostContent1))
                .andExpect(jsonPath("\$[0].averageRating").value(3))
                .andExpect(jsonPath("\$[0].reviews").isArray())
                .andExpect(jsonPath("\$[0].reviews[0].uuid").hasJsonPath())
                .andExpect(jsonPath("\$[0].reviews[0].title").value(reviewTitle1))
                .andExpect(jsonPath("\$[0].reviews[0].content").value(reviewContent1))
                .andExpect(jsonPath("\$[0].reviews[0].rating").value(rating1))

    }

    def "should return bad request"() {
        when:
        def response = mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n \"title\": \"ti\",\n \"content\": \"con\" }"))

        then:
        response.andExpect(status().is(422))
    }
}
