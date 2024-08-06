package org.example.dao;

import org.example.entity.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FilmDAO extends GenericDAO<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film findFilmByName(String name) {
        Query<Film> filmQuery = getCurrentSession().createQuery("select f from Film f where f.title = :NAME", Film.class);
        filmQuery.setParameter("NAME", name);
        filmQuery.setMaxResults(1);
        return filmQuery.getSingleResult();
    }

    public Film getFirstAvailableFindForInventory() {
        Query<Film> query = getCurrentSession().createQuery("select f from Film f where f.id not in (select i.film.id from Inventory i)", Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
