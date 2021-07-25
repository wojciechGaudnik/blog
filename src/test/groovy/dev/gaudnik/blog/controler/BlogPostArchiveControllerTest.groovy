package dev.gaudnik.blog.controler

import com.fasterxml.jackson.databind.ObjectMapper
import dev.gaudnik.blog.model.BlogPost
import dev.gaudnik.blog.repository.BlogPostRepository
import dev.gaudnik.blog.service.BlogPostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class BlogPostArchiveControllerTest extends Specification {
    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper mapper

    @Autowired
    BlogPostService blogPostService

    @Autowired
    BlogPostRepository blogPostRepository
    
    @Shared
    String url = '/blogpostarchive/'

    def "should archive BlogPost"() {
        given:
        def title = "title blog post test"
        def content = "content blog post test"
        def blogPostToArchive = BlogPost.builder().title(title).content(content).build()
        blogPostRepository.addBlogPost(blogPostToArchive)

        expect:
        mvc.perform(post(url + blogPostToArchive.getUuid())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
    }
}
