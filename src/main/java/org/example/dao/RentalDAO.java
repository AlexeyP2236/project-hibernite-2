package org.example.dao;

import org.example.entity.Inventory;
import org.example.entity.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RentalDAO extends GenericDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental findRentalWhereNull() {
        Query<Rental> rentalQuery = getCurrentSession().createQuery("select r from Rental r where r.returnDate is null", Rental.class);
        rentalQuery.setMaxResults(1);
        return rentalQuery.getSingleResult();
    }

    public Rental findRentalForPayment(List<Inventory> inventoryNumbers) {
        Query<Rental> rentalQuery = getCurrentSession().createQuery("select r from Rental r where r.inventory.id >= :START and r.inventory.id <= :END and r.rentalDate is not null", Rental.class);
        rentalQuery.setParameter("START", inventoryNumbers.get(0).getId());
        rentalQuery.setParameter("END", inventoryNumbers.get(inventoryNumbers.size() - 1));
        rentalQuery.setMaxResults(1);
        return rentalQuery.getSingleResult();
    }
}
