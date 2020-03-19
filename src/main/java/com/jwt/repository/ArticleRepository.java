package com.jwt.repository;

import com.jwt.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @RestResource(path = "articlesByKeyword")
    List<Article> findByTitleContains(@Param("mc") String mc);

    @RestResource(path = "articlesByUser")
    List<Article> findByUserEquals(@Param("user") String user);

}
