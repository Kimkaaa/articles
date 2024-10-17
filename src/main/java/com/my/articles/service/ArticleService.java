package com.my.articles.service;

import com.my.articles.dao.ArticleDAO;
import com.my.articles.dto.ArticleDTO;
import com.my.articles.entity.Article;
import com.my.articles.entity.Comment;
import com.my.articles.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleDAO dao;

    @Autowired
    ArticleRepository articleRepository;

    public List<ArticleDTO> getAllArticle() {
        List<Article> articles = dao.getAllArticle();
        if(ObjectUtils.isEmpty(articles)){
            return Collections.emptyList();
        }
        List<ArticleDTO> dtoList = articles
                .stream().map(x -> ArticleDTO.fromEntity(x))
                .toList();
        return dtoList;
    }

    public ArticleDTO getOneArticle(Long id) {
        Article article = dao.getOneArticle(id);
        if(ObjectUtils.isEmpty(article)) return null;
        return ArticleDTO.fromEntity(article);
    }

    public void deleteArticle(Long id) {
        dao.deleteArticle(id);
    }

    public void updateArticle(ArticleDTO dto) {
        dao.updateArticle(dto);
    }

    public void insertArticle(ArticleDTO dto) {
        dao.insertArticle(ArticleDTO.fromDTO(dto));
    }

    public Page<ArticleDTO> getArticlePage(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(x->ArticleDTO.fromEntity(x));
    }
}
