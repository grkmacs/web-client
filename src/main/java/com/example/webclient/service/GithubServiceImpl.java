package com.example.webclient.service;

import com.example.webclient.model.GithubUsers;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class GithubServiceImpl implements GithubService {
    private final WebClient githubClient;

    public GithubServiceImpl(WebClient githubClient) {
        this.githubClient = githubClient;
    }

    @Override
    public Flux<GithubUsers> getGithubUsers() {
        return githubClient.get().uri("users").retrieve().bodyToFlux(GithubUsers.class).retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(3)));
    }
}
