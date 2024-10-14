package com.my.articles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("articles")
public class ArticleController {
    @GetMapping("")
    public String showAllArticles() {
        return "/articles/show_all";
    }

    @GetMapping("new")
    public String newArticle() {
        return "/articles/new";
    }

    @PostMapping("create")
    public String createArticle() {
        return "redirect:articles";
    }

    @GetMapping("{id}")
    public String showOneArticle() {
        return "/articles/show";
    }

    @GetMapping("{id}/update")
    public String viewUpdateArticle() {
        return "/articles/update";
    }

    @PostMapping("update")
    public String updateArticle() {
        return "redirect:articles";
    }

    @GetMapping("{id}/delete")
    public String deleteArticle() {
        return "redirect:articles";
    }
}