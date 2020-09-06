package com.vano.controller;

import com.vano.entity.PostBlog;
import com.vano.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/home")
public class PostBlogController {
    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<PostBlog> postBlogList = postService.getAll();

        model.addAttribute("postBlog", postBlogList);
        return "list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create (Model model) {
        model.addAttribute("postBlog", new PostBlog());
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String stores(Model model, PostBlog postBlog, HttpServletRequest request) {
        try {
            postService.save(postBlog);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable Long id, Model model) {
        PostBlog postBlog = postService.findById(id);
        if (postBlog == null) {
            return "error/404";
        }

        model.addAttribute("postBlog", postBlog);
        return "detail";
    }
}
