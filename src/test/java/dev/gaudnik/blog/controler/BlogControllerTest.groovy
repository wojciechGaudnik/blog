package dev.gaudnik.blog.controler


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [BlogController])
class BlogControllerTest extends Specification {

    @Autowired
    private MockMvc mvc

    def "should throw ExceptionHandlerPost"() {
        when:
        def response = mvc.perform(get('/post/1234342134'))

        then:
        response.andExpect(status().is(422))
    }
}
