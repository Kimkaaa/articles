package com.my.articles.service;

import com.my.articles.dao.CommentDAO;
import com.my.articles.dto.CommentDTO;
import com.my.articles.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDAO commentDAO;

    public Long deleteComment(Long id) {
        return commentDAO.deleteComment(id);
    }

    public void insertComment(Long articleId, CommentDTO dto) {
        commentDAO.insertComment(articleId,
                CommentDTO.fromDTO(dto));
    }

    public Map<String, Object> findByIdComment(Long commentId) {
        Comment comment = commentDAO.findByIdComment(commentId);
        Map<String, Object> map = new HashMap<>();
        map.put("dto", CommentDTO.fromEntity(comment));
        map.put("articleId", comment.getArticle().getId());
        return map;
    }

    public void updateComment(CommentDTO dto) {
        commentDAO.updateComment(CommentDTO.fromDTO(dto));
    }
}