package com.kencuevas.shoppingsystem.services;

import com.kencuevas.shoppingsystem.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {
    ArticleDTO createArticle(long unitMeasureId, ArticleDTO articleDTO);

    List<ArticleDTO>getArticlesById(long unitMeasureId);
}
