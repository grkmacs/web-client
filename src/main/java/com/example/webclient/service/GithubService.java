package com.example.webclient.service;

import com.example.webclient.model.GithubUsers;
import reactor.core.publisher.Flux;

public interface GithubService {
    Flux<GithubUsers> getGithubUsers();
}
