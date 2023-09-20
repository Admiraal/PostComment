package com.hsleiden.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Hier kun je meerdere origins toevoegen als nodig.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Als voorbeeld
                .allowCredentials(true);
    }

}
