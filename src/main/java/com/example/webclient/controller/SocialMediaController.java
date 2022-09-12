package com.example.webclient.controller;

import com.example.webclient.model.Album;
import com.example.webclient.model.Comment;
import com.example.webclient.model.Photo;
import com.example.webclient.model.Post;
import com.example.webclient.model.Todo;
import com.example.webclient.model.User;
import com.example.webclient.service.SocialMediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/social-media")
public class SocialMediaController {

    private final SocialMediaService socialMediaService;

    public SocialMediaController(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @GetMapping("/users")
    public ResponseEntity<Flux<User>> getUsers() {
        return new ResponseEntity<>(socialMediaService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<Flux<Post>> getPosts() {
        return new ResponseEntity<>(socialMediaService.getPosts(), HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity<Flux<Todo>> getTodos() {
        return new ResponseEntity<>(socialMediaService.getTodos(), HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<Flux<Comment>> getComments() {
        return new ResponseEntity<>(socialMediaService.getComments(), HttpStatus.OK);
    }

    @GetMapping("/photos")
    public ResponseEntity<Flux<Photo>> getPhotos() {
        return new ResponseEntity<>(socialMediaService.getPhotos(), HttpStatus.OK);
    }

    @GetMapping("/albums")
    public ResponseEntity<Flux<Album>> getAlbums() {
        return new ResponseEntity<>(socialMediaService.getAlbums(), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Mono<Post>> findByPostId(@PathVariable long postId) {
        return new ResponseEntity<>(socialMediaService.findByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Mono<Comment>> findByCommentId(@PathVariable long id) {
        return new ResponseEntity<>(socialMediaService.findByCommentId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/comment")
    public ResponseEntity<Flux<Comment>> findByCommentPostId(@RequestParam long postId) {
        return new ResponseEntity<>(socialMediaService.findByCommentPostId(postId), HttpStatus.OK);
    }

    @PostMapping(value = "/posts")
    public ResponseEntity<Mono<Post>> createPost(@RequestBody Post post) {
        return new ResponseEntity<>(socialMediaService.createPost(post), HttpStatus.OK);
    }

}
