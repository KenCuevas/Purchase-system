package com.kencuevas.shoppingsystem.services;

import com.kencuevas.shoppingsystem.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {
    ArticleDTO createArticle(long measureId, ArticleDTO articleDTO);

    List<ArticleDTO>getArticlesByMeasureId(long measureId);

    ArticleDTO getArticlesById(Long measureId, Long articleId);

    ArticleDTO updateArticle(Long measureId, long articleId, ArticleDTO articleRequest);

    void deleteArticle(Long measureId, Long articleId);

    List<ArticleDTO>getAllArticles();
}
