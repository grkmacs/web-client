package com.example.webclient.service;

import com.example.webclient.model.Album;
import com.example.webclient.model.Comment;
import com.example.webclient.model.Photo;
import com.example.webclient.model.Post;
import com.example.webclient.model.Todo;
import com.example.webclient.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SocialMediaService {
    Flux<User> getUsers();

    Flux<Post> getPosts();

    Flux<Todo> getTodos();

    Flux<Comment> getComments();

    Flux<Photo> getPhotos();

    Flux<Album> getAlbums();

    Mono<Post> findByPostId(long postId);

    Mono<Comment> findByCommentId(long id);

    Flux<Comment> findByCommentPostId(long postId);

    Mono<Post> createPost(Post post);
}
