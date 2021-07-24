package dev.gaudnik.blog

import dev.gaudnik.blog.controler.BlogController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class BlogApplicationTests extends Specification {

    @Autowired(required = false)
    private BlogController blogController

    def "load context"() {
        expect: "the BlogController is loaded"
        blogController;
    }


}
