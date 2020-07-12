package com;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        String[][] mappings = {
                {"/", "menu"}
                , {"/admin", "admin"}
                , {"/guest", "guest"}
                , {"/home", "home"}
                , {"/news", "news"}
                , {"/me", "me"}
        };
        for (String[] site : mappings) {
            registry.addViewController(site[0]).setViewName(site[1]);
        }
    }
}
