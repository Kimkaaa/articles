package com.my.articles.dto;

import com.my.articles.entity.Article;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;

    public static ArticleDTO fromEntity(Article article){
        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getContent());
    }

    public static Article fromDTO(ArticleDTO dto){
        Article article = new Article();
        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        return article;
    }
}