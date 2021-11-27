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
}
