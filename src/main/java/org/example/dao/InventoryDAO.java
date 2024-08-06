package org.example.dao;

import org.example.entity.Inventory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class InventoryDAO extends GenericDAO<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    public List<Inventory> findInventoryByFilmId(Short id) {
        Query<Inventory> inventoryQuery = getCurrentSession().createQuery("select i from Inventory i where i.film.id = :FILM_ID", Inventory.class);
        inventoryQuery.setParameter("FILM_ID", id);
        return inventoryQuery.getResultList();
    }
}
