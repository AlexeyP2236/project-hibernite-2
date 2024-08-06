package org.example.service;

import org.example.dao.RentalDAO;
import org.example.entity.Rental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class RentalService {

    private final SessionFactory sessionFactory;
    private final RentalDAO rentalDAO;

    public RentalService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        this.rentalDAO = new RentalDAO(sessionFactory);
    }

    public void refundFilm() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Rental rental = rentalDAO.findRentalWhereNull();
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.update(rental);

            transaction.commit();
        }
    }
}
