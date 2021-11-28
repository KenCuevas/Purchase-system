package com.kencuevas.shoppingsystem.controllers;

import com.kencuevas.shoppingsystem.dto.ArticleDTO;
import com.kencuevas.shoppingsystem.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    // This function allows the user to add new articles to the database
    @PostMapping("/units/{measureId}/articles")
    public ResponseEntity<ArticleDTO>createArticle(@PathVariable(value = "measureId") long measureId,
                                                   @RequestBody ArticleDTO articleDTO){
        return new ResponseEntity<>(articleService.createArticle(measureId, articleDTO), HttpStatus.CREATED);
    }
    @GetMapping("/units/{measureId}/articles")
    public List<ArticleDTO>getArticleByMeasureId(@PathVariable(value = "measureId") Long measureId){
        return articleService.getArticlesByMeasureId(measureId);
    }
    @GetMapping("/units/{measureId}/articles/{id}")
    public ResponseEntity<ArticleDTO>getArticleById(@PathVariable(value = "measureId") Long measureId,
                                                    @PathVariable(value = "id") Long articleId){
        ArticleDTO articleDTO = articleService.getArticlesById(measureId, articleId);

        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }
    @PutMapping("/units/{measureId}/update/article/{id}")
    public ResponseEntity<ArticleDTO>updateArticle(@PathVariable(value = "measureId") Long measureId,
                                                   @PathVariable(value = "id") Long articleId,
                                                   @RequestBody ArticleDTO articleDTO){
        ArticleDTO updateArticle = articleService.updateArticle(measureId, articleId, articleDTO);
        return new ResponseEntity<>(updateArticle, HttpStatus.OK);
    }
    @DeleteMapping("/units/{measureId}/delete/article/{id}")
    public ResponseEntity<String>deleteArticle(@PathVariable(value = "measureId") Long measureId,
                                               @PathVariable(value = "id") Long articleId){
        articleService.deleteArticle(measureId, articleId);
        return new ResponseEntity<>("Article deleted successfully", HttpStatus.OK);
    }
    @GetMapping("/all/articles")
    public List<ArticleDTO>getAllArticle(){
        return articleService.getAllArticles();
    }
}
