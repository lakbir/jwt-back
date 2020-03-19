package com.jwt;

import com.jwt.entities.Article;
import com.jwt.entities.Category;
import com.jwt.entities.ERole;
import com.jwt.repository.ArticleRepository;
import com.jwt.repository.CategoyRepository;
import com.jwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class JwtBackApplication implements CommandLineRunner {

	@Autowired
	private CategoyRepository categoyRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(JwtBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repositoryRestConfiguration.exposeIdsFor(Article.class, Category.class);
		//categoyRepository.save(new Category(null, "Categorie 1","cat.png", "Description categorie 1", null));
		//categoyRepository.save(new Category(null, "Categorie 2","cat.png", "Description categorie 2", null));
		//categoyRepository.save(new Category(null, "Categorie 3","cat.png", "Description categorie 3", null));
		//categoyRepository.save(new Category(null, "Categorie 4","cat.png", "Description categorie 4", null));

		//Date yourDate = new Date();
		//SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

/*
		categoyRepository.findAll().forEach(c->{
			for (int i = 0; i < 10; i++) {
				Article art = new Article();
				art.setTitle("Article "+i+", Categorie "+c.getId());
				art.setDescription("Le lorem ipsum est, en imprimerie, une suite de mots sans signification utilisée à titre provisoire pour calibrer une mise en page, le texte définitif venant remplacer le faux-texte dès qu'il est prêt ou que la mise en page est achevée. Généralement, on utilise un texte en faux latin, le Lorem ipsum ou Lipsum");
				art.setPhoto("article.png");
				art.setCategory(c);
				art.setUser("user");
				art.setDateAdd(DATE_FORMAT.format(yourDate));
				articleRepository.save(art);
			}
		});
		*/
	}
}
