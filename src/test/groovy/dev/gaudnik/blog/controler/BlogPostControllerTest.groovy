package dev.gaudnik.blog.controler

import com.fasterxml.jackson.databind.ObjectMapper
import dev.gaudnik.blog.service.BlogPostService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [BlogPostController])
class BlogPostControllerTest extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BlogPostService blogPostService;

    @MockBean
    private ModelMapper modelMapper;

    def "should throw ExceptionHandlerPost"() {
        when:
        def response = mvc.perform(get('/blogpost/57462537-434b-47c7-91c1-e7f8f2e5be52'))

        then:
        response.andExpect(status().is(422))
    }


    def "should add BlogPost"() {
        when:
        def response = mvc.perform(post('/blogpost/')
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n \"title\": \"title blog post test\",\n \"content\": \"content blog post test\" }"))

        then:
        response.andExpect(status().isCreated())
    }


    def "should return bad request"() {
        when:
        def response = mvc.perform(post('/blogpost/')
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n \"title\": \"ti\",\n \"content\": \"con\" }"))

        then:
        response.andExpect(status().is(422))
    }
}
