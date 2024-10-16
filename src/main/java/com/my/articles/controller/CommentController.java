package com.my.articles.controller;

import com.my.articles.dto.CommentDTO;
import com.my.articles.service.ArticleService;
import com.my.articles.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("comments/{id}")
    public String deleteComment(@PathVariable("id")Long id){
        Long articleId = commentService.deleteComment(id);
        return "redirect:/articles/" + articleId;
    }

    @PostMapping("{id}/comments")
    public String insertComment(CommentDTO dto,
                                @PathVariable("id")Long articleId){
        dto.setId(null);
        commentService.insertComment(articleId, dto);
        return "redirect:/articles/" + articleId;
    }

    @GetMapping("comments/view/{id}")
    public String updateCommentView(@PathVariable("id") Long commentId,
            Model model){
        Map<String, Object> map = commentService.findByIdComment(commentId);
        model.addAttribute("dto", map.get("dto"));
        model.addAttribute("articleId", map.get("articleId"));
        return "/articles/update_comment";
    }

    @PostMapping("{article_id}/comments/{comment_id}")
    public String updateComment(CommentDTO dto,
                                @PathVariable("article_id")Long article_id,
                                @PathVariable("comment_id")Long comment_id){
        dto.setId(comment_id);
        commentService.updateComment(dto);
        return "redirect:/articles/" + article_id;
    }
}