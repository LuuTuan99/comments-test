package com.vano.controller;

import com.vano.entity.Comment;
import com.vano.entity.PostBlog;
import com.vano.service.CommentService;
import com.vano.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping(value = "/comments")
public class CommentController {
    private PostService postService;
    private CommentService commentService;

    public CommentController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String stores(Comment comment) {
        try {
            commentService.save(comment);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return "redirect:/home/detail/" + comment.getPostBlog().getId();
    }

    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    public String createComment(@PathVariable Long id, Model model) {
        Optional<PostBlog> postBlog = postService.findById(id);

        if (postBlog.isPresent()) {
            Comment comment = new Comment();
            comment.setPostBlog(postBlog.get());
            model.addAttribute("comment", comment);
            return "comments/create";
        } else {
            return "error";
        }
    }


}
