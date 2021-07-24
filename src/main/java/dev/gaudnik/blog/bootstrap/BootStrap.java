package dev.gaudnik.blog.bootstrap;

import dev.gaudnik.blog.model.BlogPost;
import dev.gaudnik.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

	@Value("${blog.bootstrap}")
	String bootstrap;

	private final BlogPostRepository blogPostRepository;

	public BootStrap(BlogPostRepository blogPostRepository) {
		this.blogPostRepository = blogPostRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		if (bootstrap.equals("true")) {
			var blogPost1 = BlogPost.builder().title("title blog post 1").content("content blog post 1").build();
			blogPostRepository.addBlogPost(blogPost1);
		}
	}


}
