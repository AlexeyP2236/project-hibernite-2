package org.example.dao;

import org.example.entity.Category;
import org.hibernate.SessionFactory;

public class CategotyDAO extends GenericDAO<Category> {

    public CategotyDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
