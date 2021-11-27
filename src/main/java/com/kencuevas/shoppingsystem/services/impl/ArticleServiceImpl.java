package com.kencuevas.shoppingsystem.services.impl;

import com.kencuevas.shoppingsystem.dto.ArticleDTO;
import com.kencuevas.shoppingsystem.exceptions.ResourceNotFoundException;
import com.kencuevas.shoppingsystem.models.Article;
import com.kencuevas.shoppingsystem.models.UnitMeasure;
import com.kencuevas.shoppingsystem.repositories.ArticleRepository;
import com.kencuevas.shoppingsystem.repositories.UnitMeasureRepository;
import com.kencuevas.shoppingsystem.services.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;
    private UnitMeasureRepository measureRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, UnitMeasureRepository measureRepository) {
        this.articleRepository = articleRepository;
        this.measureRepository = measureRepository;
    }


    @Override
    public ArticleDTO createArticle(long measureId, ArticleDTO articleDTO) {
        Article article = mapToEntity(articleDTO);
        // Retrieve measure entity by Id
        UnitMeasure measure = measureRepository.findById(measureId).orElseThrow(
                ()-> new ResourceNotFoundException("Unit measure", "id",measureId));

        article.setMeasure(measure);

        // Save article entity to database
        Article newArticle = articleRepository.save(article);
        return mapToDTO(newArticle);
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
