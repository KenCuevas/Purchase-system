package com.kencuevas.shoppingsystem.services.impl;

import com.kencuevas.shoppingsystem.dto.ArticleDTO;
import com.kencuevas.shoppingsystem.exceptions.PurchaseApiException;
import com.kencuevas.shoppingsystem.exceptions.ResourceNotFoundException;
import com.kencuevas.shoppingsystem.models.Article;
import com.kencuevas.shoppingsystem.models.UnitMeasure;
import com.kencuevas.shoppingsystem.repositories.ArticleRepository;
import com.kencuevas.shoppingsystem.repositories.UnitMeasureRepository;
import com.kencuevas.shoppingsystem.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
    private ModelMapper mapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, UnitMeasureRepository measureRepository, ModelMapper mapper) {
        this.articleRepository = articleRepository;
        this.measureRepository = measureRepository;
        this.mapper = mapper;
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

    @Override
    public List<ArticleDTO> getArticlesByMeasureId(long measureId) {
        // Retrieve articles by measureId
        List<Article> articles = articleRepository.findByMeasureId(measureId);

        // Convert list of articles entities to list of articles DTO'S
        return articles.stream().map(article -> mapToDTO(article)).collect(Collectors.toList());
    }

    @Override
    public ArticleDTO getArticlesById(Long measureId, Long articleId) {
        // Retrieve measure entity by Id
        UnitMeasure measure = measureRepository.findById(measureId).orElseThrow(
                ()-> new ResourceNotFoundException("Unit measure", "id",measureId));

        //Retrieve articles by id
        Article article = articleRepository.findById(articleId).orElseThrow(()->
                new ResourceNotFoundException("Article", "id", articleId));

        if(!article.getMeasure().getId().equals(measure.getId())){
            throw new PurchaseApiException(HttpStatus.BAD_REQUEST, "Article does not belong to unit measure");
        }
        return mapToDTO(article);
    }

    @Override
    public ArticleDTO updateArticle(Long measureId, long articleId, ArticleDTO articleRequest) {
        // Retrieve measure entity by Id
        UnitMeasure measure = measureRepository.findById(measureId).orElseThrow(
                ()-> new ResourceNotFoundException("Unit measure", "id", measureId));
        //Retrieve articles by id
        Article article = articleRepository.findById(articleId).orElseThrow(()->
                new ResourceNotFoundException("Article", "id", articleId));
        if(!article.getMeasure().getId().equals(measure.getId())){
            throw new PurchaseApiException(HttpStatus.BAD_REQUEST, "Article does not belongs to unit measure");
        }
        article.setDescription(articleRequest.getDescription());
        article.setBrand(articleRequest.getBrand());
        article.setAvailability(articleRequest.getAvailability());
        article.setStatus(articleRequest.isStatus());
        Article updateArticle = articleRepository.save(article);

        return mapToDTO(updateArticle);
    }

    @Override
    public void deleteArticle(Long measureId, Long articleId) {
        // Retrieve measure entity by Id
        UnitMeasure unitMeasure = measureRepository.findById(measureId).orElseThrow(
                ()-> new ResourceNotFoundException("Unit measure", "id", measureId));

        //Retrieve articles by id
        Article articles = articleRepository.findById(articleId).orElseThrow(()->
                new ResourceNotFoundException("Article", "id", articleId));

        if(!articles.getMeasure().getId().equals(unitMeasure.getId())){
            throw new PurchaseApiException(HttpStatus.BAD_REQUEST, "Article does not belongs to unit measure");
        }
        articleRepository.delete(articles);
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        List<Article>articleList=articleRepository.findAll();
        return articleList.stream().map(article -> mapToDTO(article)).collect(Collectors.toList());
    }

    private ArticleDTO mapToDTO(Article article){
        ArticleDTO articleDTO = mapper.map(article, ArticleDTO.class);
//        ArticleDTO articleDTO = new ArticleDTO();
//        articleDTO.setId(article.getId());
//        articleDTO.setDescription(article.getDescription());
//        articleDTO.setBrand(article.getBrand());
//        articleDTO.setAvailability(article.getAvailability());
//        articleDTO.setStatus(article.isStatus());
        return articleDTO;
    }

    private Article mapToEntity(ArticleDTO articleDTO){
        Article article = mapper.map(articleDTO, Article.class);
//        Article article = new Article();
//         article.setId(articleDTO.getId());
//         article.setDescription(articleDTO.getDescription());
//         article.setBrand(articleDTO.getBrand());
//         article.setAvailability(articleDTO.getAvailability());
//         article.setStatus(articleDTO.isStatus());
         return article;
    }
//    public UnitMeasure getMyMeasureId(){
//       UnitMeasure measure = measureRepository.findById(measureId).orElseThrow(
//               ()-> new ResourceNotFoundException("Unit measure", "id", measureId)
//       );
//       return measure;
//    }


}
