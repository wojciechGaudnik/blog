package dev.gaudnik.blog.controler

import com.fasterxml.jackson.databind.ObjectMapper
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
class BlogPostControllerTest extends Specification {

    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper mapper

    @Autowired
    RatingVOConfig ratingVOConfig

    @MockBean
    BlogPostService blogPostService

    @Shared
    String url = '/blogpost/'
    def "should create BlogPost"() {
        given:
        def title = "title blog post test"
        def content = "content blog post test"
        when(blogPostService.addBlogPost(any(BlogPostAddRequest.class))).thenReturn(BlogPost.builder().title(title).content(content).build())


        expect:
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n \"title\": \"" + title + "\",\n \"content\": \"" + content + "\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("\$.title").value(title))
                .andExpect(jsonPath("\$.content").value(content))
                .andExpect(jsonPath("\$.uuid").hasJsonPath())
    }

    def "should return all BlogPosts"() {
        given:
        def reviewTitle1 = "review title 1"
        def reviewTitle2 = "review title 2"
        def reviewContent1 = "review content title 1"
        def reviewContent2 = "review content title 2"
        def blogPostTitle1 = "blog post title 1"
        def blogPostTitle2 = "blog post title 2"
        def blogPostContent1 = "blog post content 1"
        def blogPostContent2 = "blog post content 2"
        def rating1 = 2
        def rating2 = 4
        def ratingVo1 = RatingVO.builder().rating(rating1).ratingVOConfig(ratingVOConfig).build()
        def ratingVo2 = RatingVO.builder().rating(rating2).ratingVOConfig(ratingVOConfig).build()
        def review1 = Review.builder().title(reviewTitle1).content(reviewContent1).rating(ratingVo1).build()
        def review2 = Review.builder().title(reviewTitle2).content(reviewContent2).rating(ratingVo2).build()
        def blogPost1 = BlogPost.builder().title(blogPostTitle1).content(blogPostContent1).build()
        def blogPost2 = BlogPost.builder().title(blogPostTitle2).content(blogPostContent2).build()
        blogPost1.addReview(review1)
        blogPost1.addReview(review2)
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
