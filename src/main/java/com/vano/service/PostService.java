package com.vano.service;

import com.vano.entity.PostBlog;
import com.vano.repository.PostBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        postBlog.setCreated_at(formatter.format(date));
        return postBlogRepository.save(postBlog);
    }
}
