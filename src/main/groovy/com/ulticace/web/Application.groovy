package com.ulticace.web

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.view.InternalResourceViewResolver

/**
 * Created by Matt Rossner on 5/16/16.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ulticace")
@Configuration
class Application {
    static main(args) {
        new SpringApplicationBuilder()
                .sources(Application.class)
                .bannerMode(Banner.Mode.OFF)
                .run()
    }

    @Bean
    def viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver()
        resolver.prefix = 'WEB-INF/jsp/'
        resolver.suffix = '.jsp'
        resolver
    }
}
