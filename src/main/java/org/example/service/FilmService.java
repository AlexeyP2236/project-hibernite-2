package org.example.service;

import org.example.config.MySessionFactory;
import org.example.dao.*;
import org.example.entity.Actor;
import org.example.entity.Category;
import org.example.entity.Film;
import org.example.entity.FilmText;
import org.example.entity.enumm.Features;
import org.example.entity.enumm.Rating;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmService {
    private final SessionFactory sessionFactory;
    private final ActorDAO actorDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final LanguageDAO languageDAO;

    public FilmService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.filmDAO = new FilmDAO(MySessionFactory.getSessionFactory());
        this.filmTextDAO = new FilmTextDAO(MySessionFactory.getSessionFactory());
        this.languageDAO = new LanguageDAO(MySessionFactory.getSessionFactory());
        this.actorDAO = new ActorDAO(MySessionFactory.getSessionFactory());
    }


    public Film newFilm() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Film film = new Film();
            film.setTitle("Escape from the umbrella".toUpperCase());
            film.setDescription("A group of people are trying to escape from a closed experimental area");
            film.setReleaseYear(Short.valueOf("2010"));
            film.setLanguage(languageDAO.getById(Byte.valueOf("2")));
            List<Actor> actors = actorDAO.getItems(20, 26);
            film.setActors(actors);
            List<Category> categories = actorDAO.findCategory("Action", "Comedy", "Drama");
            film.setCategories(categories);
            film.setRentalDuration(Byte.valueOf("6"));
            film.setRentalRate(BigDecimal.valueOf(4.99));
            film.setLength(Short.valueOf("29"));
            film.setReplacementCost(BigDecimal.valueOf(1.99));
            film.setRating(Rating.G);
            Set<Features> features = new HashSet<>();
            features.add(Features.COMMENTARIES);
            features.add(Features.DELETED_SCENES);
            film.setSpecialFeatures(features);

            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setFilm_id(film.getId());
            filmText.setTitle(film.getTitle());
            filmText.setDescription(film.getDescription());

            filmTextDAO.save(filmText);

            session.getTransaction().commit();
            return film;
        }
    }
}
