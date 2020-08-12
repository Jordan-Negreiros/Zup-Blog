package com.jordan.zupblog.resources;

import com.jordan.zupblog.domain.Post;
import com.jordan.zupblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    private final PostService postService;

    @Autowired
    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> postList = postService.findAll();
        return ResponseEntity.ok().body(postList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findPostsById(@PathVariable Integer id) {
        Post foundPost = postService.find(id);
        return ResponseEntity.ok().body(foundPost);
    }

    @PostMapping
    public ResponseEntity<Void> insertPost(@RequestBody Post post) {
        Post newPost = postService.create(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updatePost(@RequestBody Post post, @PathVariable Integer id) {

        Post foundPost = post;
        foundPost.setId(id);
        foundPost = postService.update(post);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
