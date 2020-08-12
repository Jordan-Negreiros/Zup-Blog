package com.jordan.zupblog.repositories;

import com.jordan.zupblog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // JPQL
    @Query("SELECT p from Post p where p.titulo = ?1")
    Post findPostByTitulo(String titulo);

    // Query Methods
    List<Post> findByTituloOrderById(String titulo);

    // SQL
    @Query(value = "SELECT titulo FROM POST;", nativeQuery = true)
    String getTitulos();
}
