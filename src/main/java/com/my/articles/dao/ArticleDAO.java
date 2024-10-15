package com.my.articles.dao;

import com.my.articles.dto.ArticleDTO;
import com.my.articles.entity.Article;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Component
public class ArticleDAO {
    @Autowired
    EntityManager em;

    public List<Article> getAllArticle() {
        String sql = "SELECT a FROM Article a ORDER BY a.id DESC";
        return em.createQuery(sql).getResultList();
    }

    public Article getOneArticle(Long id) {
        return em.find(Article.class, id);
    }
}
