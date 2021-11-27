package com.kencuevas.shoppingsystem.services.impl;

import com.kencuevas.shoppingsystem.dto.ArticleDTO;
import com.kencuevas.shoppingsystem.exceptions.ResourceNotFoundException;
import com.kencuevas.shoppingsystem.models.Article;
import com.kencuevas.shoppingsystem.models.UnitMeasure;
import com.kencuevas.shoppingsystem.repositories.ArticleRepository;
import com.kencuevas.shoppingsystem.repositories.UnitMeasureRepository;
import com.kencuevas.shoppingsystem.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;
    private UnitMeasureRepository unitMeasureRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UnitMeasureRepository unitMeasureRepository) {
        this.articleRepository = articleRepository;
        this.unitMeasureRepository = unitMeasureRepository;
    }

    @Override
    public ArticleDTO createArticle(long unitMeasureId, ArticleDTO articleDTO) {
        Article article = mapToEntity(articleDTO);
        // Retrieve article entity by id
        UnitMeasure unitMeasure = unitMeasureRepository.findById(unitMeasureId).orElseThrow(
                ()-> new ResourceNotFoundException("Unit measure", "id", unitMeasureId));
        // Set unit measure to article entity
        article.setUnitMeasure(unitMeasure);
        // article entity to DB
        Article newArticle = articleRepository.save(article);
        return mapToDTO(newArticle);
    }

    @Override
    public List<ArticleDTO> getArticlesById(long unitMeasureId) {
        // Retrieve articles by units id
        List<Article> articles = articleRepository.findByUnitId(unitMeasureId);
        // convert list of articles entities to list of articles DTO's
        return articles.stream().map(article -> mapToDTO(article)).collect(Collectors.toList());
    }


    private ArticleDTO mapToDTO(Article article){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setDescription(article.getDescription());
        articleDTO.setBrand(article.getBrand());
        articleDTO.setAvailability(article.getAvailability());
        articleDTO.setStatus(article.isStatus());

        return articleDTO;
    }

    private Article mapToEntity(ArticleDTO articleDTO){
        Article article = new Article();
         article.setId(articleDTO.getId());
         article.setDescription(articleDTO.getDescription());
         article.setBrand(articleDTO.getBrand());
         article.setAvailability(articleDTO.getAvailability());
         article.setStatus(articleDTO.isStatus());

         return article;
    }
}
