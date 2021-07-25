package dev.gaudnik.blog.controler

import dev.gaudnik.blog.model.BlogPost
import dev.gaudnik.blog.repository.BlogPostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
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

    def "should create BlogPost"() {
        given:
        def reviewTitle = "title review test"
        def reviewContent = "content review test"
        def reviewRating = 3
        def blogPostTitle = "title blog post test"
        def blogPostContent = "content blog post test"
        def blogPost = BlogPost.builder()
                .title(blogPostTitle)
                .content(blogPostContent).build()
        blogPostRepository.addBlogPost(blogPost)

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

}
