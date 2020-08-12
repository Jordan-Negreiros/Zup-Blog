package com.jordan.zupblog.services;

import com.jordan.zupblog.domain.Post;
import com.jordan.zupblog.repositories.PostRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository repository;

    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post find(Integer id) {
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException(id, Post.class.getName()));
    }

    public Post create(Post post) {
        return repository.save(post);
    }

    public Post update(Post post) {

        Post newPost = find(post.getId());

        newPost.setTitulo(post.getTitulo());
        newPost.setConteudo(post.getConteudo());

        return repository.save(post);
    }

    public void delete(Integer id) {
        find(id);
        repository.deleteById(id);
    }

}
