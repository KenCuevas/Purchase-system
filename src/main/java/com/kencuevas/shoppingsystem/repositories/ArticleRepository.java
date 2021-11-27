package com.kencuevas.shoppingsystem.repositories;

import com.kencuevas.shoppingsystem.dto.ArticleDTO;
import com.kencuevas.shoppingsystem.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article>findByUnitId(long unitMeasureId);
}