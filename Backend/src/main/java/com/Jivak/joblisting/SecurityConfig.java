package com.Jivak.joblisting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for testing; enable it in production
                .authorizeRequests()
                .antMatchers("/user/signup", "/user/login", "/post","/allPosts" ,"/posts/", "/posts/${query} ","/resume/upload").permitAll() // Allow signup, login, and posting without authentication
                .anyRequest().authenticated(); // Require authentication for all other endpoints

        return http.build();
    }

    // Enable CORS support
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("${frontend.url}") // Allow frontend requests from React (running on localhost:3000)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }
}
