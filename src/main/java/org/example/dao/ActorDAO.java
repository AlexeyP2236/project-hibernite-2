package org.example.dao;

import org.example.entity.Actor;
import org.example.entity.Category;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ActorDAO extends GenericDAO<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }

    public List<Category> findCategory(String... valuesCategory) {
        List<Category> categories = new ArrayList<>();
        for (String value : valuesCategory) {
            Query<Category> query = getCurrentSession().createQuery("select c from Category c where c.name = :NAME", Category.class);
            query.setParameter("NAME", value);
            categories.addAll(query.list());
        }

        if (categories.isEmpty()){
            throw new RuntimeException("Category is empty");
        }
        return categories;
    }
}
