package com.jwt.controllers;

import com.jwt.entities.Article;
import com.jwt.entities.Category;
import com.jwt.repository.ArticleRepository;
import com.jwt.repository.CategoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoyRepository categoyRepository;


    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Article addArticle(@RequestBody Article article) {
        Date yourDate = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        article.setDateAdd(DATE_FORMAT.format(yourDate));
        article.setPhoto("article.png");
        return articleRepository.save(article);
    }
}
