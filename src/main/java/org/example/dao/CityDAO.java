package org.example.dao;

import org.example.entity.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CityDAO extends GenericDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City getByName(String name) {
        Query<City> cityQuery = getCurrentSession().createQuery("select c from City c where c.city = :NAME", City.class);
        cityQuery.setParameter("NAME", name);
        cityQuery.setMaxResults(1);
        return cityQuery.getSingleResult();
    }
}