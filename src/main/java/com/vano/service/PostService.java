package com.vano.service;

import com.vano.entity.PostBlog;
import com.vano.repository.PostBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostBlogRepository postBlogRepository;

    public List<PostBlog> getAll(){
        return postBlogRepository.findAll();
    }

    public PostBlog findById(Long id){
        return postBlogRepository.findById(id).orElse(null);
    }

    public PostBlog save(PostBlog postBlog) {
        return postBlogRepository.save(postBlog);
    }
}
