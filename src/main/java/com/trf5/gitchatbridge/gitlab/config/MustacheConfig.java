package com.trf5.gitchatbridge.gitlab.config;

import com.samskivert.mustache.Mustache;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MustacheConfig {

    @Bean
    public MustacheViewResolver mustacheViewResolver(Mustache.Compiler mustacheCompiler) {
        MustacheViewResolver resolver = new MustacheViewResolver(mustacheCompiler);
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".mustache"); // ou .mustache
        return resolver;
    }

    @Bean
    public Mustache.Compiler mustacheCompiler() {
        return Mustache.compiler();
    }
}
