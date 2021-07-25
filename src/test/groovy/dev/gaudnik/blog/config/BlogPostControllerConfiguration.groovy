package dev.gaudnik.blog.config


import spock.lang.Shared

interface BlogPostControllerConfiguration {

    @Shared
    String url = '/blogpost/'

    @Shared
    def reviewTitle1 = "review title 1"

    @Shared
    def reviewTitle2 = "review title 2"

    @Shared
    def reviewContent1 = "review content title 1"

    @Shared
    def reviewContent2 = "review content title 2"

    @Shared
    def blogPostTitle1 = "blog post title 1"

    @Shared
    def blogPostTitle2 = "blog post title 2"

    @Shared
    def blogPostContent1 = "blog post content 1"

    @Shared
    def blogPostContent2 = "blog post content 2"

    @Shared
    def rating1 = 2

    @Shared
    def rating2 = 4
}