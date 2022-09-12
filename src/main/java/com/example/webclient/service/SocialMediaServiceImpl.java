package com.example.webclient.service;

import com.example.webclient.model.Album;
import com.example.webclient.model.Comment;
import com.example.webclient.model.Photo;
import com.example.webclient.model.Post;
import com.example.webclient.model.Todo;
import com.example.webclient.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class SocialMediaServiceImpl implements SocialMediaService {

    private final WebClient jsonPlaceHolderClient;

    public SocialMediaServiceImpl(WebClient jsonPlaceHolderClient) {
        this.jsonPlaceHolderClient = jsonPlaceHolderClient;
    }

    @Override
    public Flux<User> getUsers() {
        return jsonPlaceHolderClient.get().uri("users").retrieve().bodyToFlux(User.class);
    }

    @Override
    public Flux<Post> getPosts() {
        return jsonPlaceHolderClient.get().uri("posts").retrieve().bodyToFlux(Post.class).retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(3)));
    }

    @Override
    public Flux<Todo> getTodos() {
        return jsonPlaceHolderClient.get().uri("todos").retrieve().bodyToFlux(Todo.class);
    }

    @Override
    public Flux<Comment> getComments() {
        return jsonPlaceHolderClient.get().uri("comments").retrieve().bodyToFlux(Comment.class);
    }

    @Override
    public Flux<Photo> getPhotos() {
        return jsonPlaceHolderClient.get().uri("photos").retrieve().bodyToFlux(Photo.class).retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(3)));
    }

    @Override
    public Flux<Album> getAlbums() {
        return jsonPlaceHolderClient.get().uri("albums").retrieve().bodyToFlux(Album.class);
    }

    @Override
    public Mono<Post> findByPostId(long postId) {
        return jsonPlaceHolderClient.get().uri(uriBuilder -> uriBuilder.pathSegment("posts", "{postId}").build(postId)).retrieve().bodyToMono(Post.class);
    }

    @Override
    public Mono<Comment> findByCommentId(long id) {
        return jsonPlaceHolderClient.get().uri(uriBuilder -> uriBuilder.pathSegment("comments", "{id}").build(id)).retrieve().onStatus(HttpStatus::is2xxSuccessful, error -> Mono.error(new RuntimeException("Başarılı yanıt döndüğünde hata fırlat"))).bodyToMono(Comment.class);
    }

    @Override
    public Flux<Comment> findByCommentPostId(long postId) {
        return jsonPlaceHolderClient.get().uri(uriBuilder -> uriBuilder.path("comments").queryParam("postId", postId).build()).retrieve().bodyToFlux(Comment.class);
    }

    @Override
    public Mono<Post> createPost(Post post) {
        return jsonPlaceHolderClient.post().uri("posts").body(Mono.just(post), Post.class).retrieve().bodyToMono(Post.class);
    }
}
