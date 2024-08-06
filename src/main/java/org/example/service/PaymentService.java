package org.example.service;

import org.example.dao.*;
import org.example.entity.Customer;
import org.example.entity.Inventory;
import org.example.entity.Payment;
import org.example.entity.Rental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PaymentService {
    private final SessionFactory sessionFactory;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final StoreDAO storeDAO;
    private final InventoryDAO inventoryDAO;
    private final StaffDAO staffDAO;
    private final RentalDAO rentalDAO;
    private final PaymentDAO paymentDAO;

    private Short customerId;

    public PaymentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.customerDAO = new CustomerDAO(sessionFactory);
        this.filmDAO = new FilmDAO(sessionFactory);
        this.storeDAO = new StoreDAO(sessionFactory);
        this.inventoryDAO = new InventoryDAO(sessionFactory);
        this.staffDAO = new StaffDAO(sessionFactory);
        this.rentalDAO = new RentalDAO(sessionFactory);
        this.paymentDAO = new PaymentDAO(sessionFactory);
    }

    public void setCustomerId(short customerId){
        this.customerId = customerId;
    }
    public void rentalOfInventory() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Customer customer = customerDAO.getById(customerId);

            Inventory inventory = new Inventory();
            inventory.setFilm(filmDAO.getFirstAvailableFindForInventory());
            inventory.setStore(storeDAO.getById(Byte.valueOf("2")));
            inventoryDAO.save(inventory);

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setInventory(inventory);
            rental.setCustomer(customer);
            rental.setReturnDate(null);
            rental.setStaff(staffDAO.getById(Byte.valueOf("2")));
            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setStaff(staffDAO.getById(Byte.valueOf("2")));
            payment.setRental(rental);
            payment.setAmount(BigDecimal.valueOf(4.99));
            payment.setPaymentDate(Timestamp.valueOf(LocalDateTime.now()));
            paymentDAO.save(payment);

            transaction.commit();
        }
    }
}
