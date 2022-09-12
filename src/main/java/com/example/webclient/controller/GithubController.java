package com.example.webclient.controller;

import com.example.webclient.model.GithubUsers;
import com.example.webclient.service.GithubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/github")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/users")
    public ResponseEntity<Flux<GithubUsers>> githubUsers() {
        return new ResponseEntity<>(githubService.getGithubUsers(), HttpStatus.OK);
    }
}
