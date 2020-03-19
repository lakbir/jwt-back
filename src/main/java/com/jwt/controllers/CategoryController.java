package com.jwt.controllers;

import com.jwt.auth.request.SignupRequest;
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
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoyRepository categoyRepository;


    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Category addCategory(@RequestBody Category category) {
        category.setPhoto("categorie.png");
        return categoyRepository.save(category);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Category updateCategory(@RequestBody Category category) {
        return categoyRepository.save(category);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public boolean booleanCategory(@RequestParam(name = "id") Long id) {
        categoyRepository.deleteById(id);
        return true;
    }



}
