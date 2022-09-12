package com.example.webclient.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfiguration {

    @Value("${spring.json.place.holder.api.url}")
    private String jsonPlaceHolderBaseUrl;

    @Value("${spring.github.api.url}")
    private String githubBaseUrl;

    @Bean
    public WebClient jsonPlaceHolderClient() {
        return WebClient.builder()
                .baseUrl(jsonPlaceHolderBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient githubClient() {
        return WebClient.builder()
                .baseUrl(githubBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
