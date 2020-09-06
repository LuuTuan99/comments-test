package com.vano.repository;

import com.vano.entity.PostBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostBlogRepository extends JpaRepository<PostBlog, Long> {
    Optional<PostBlog> findById(Long id);
}
