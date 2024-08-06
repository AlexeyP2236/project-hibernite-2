package org.example;

import org.example.config.MySessionFactory;
import org.example.entity.Customer;
import org.example.entity.Film;
import org.example.service.CustomerService;
import org.example.service.FilmService;
import org.example.service.PaymentService;
import org.example.service.RentalService;
import org.hibernate.SessionFactory;

public class AppNew {
    private final CustomerService customerService;
    private final RentalService rentalService;
    private final PaymentService paymentService;
    private final FilmService filmService;

    public AppNew(SessionFactory sessionFactory) {
        this.customerService = new CustomerService(sessionFactory);
        this.rentalService = new RentalService(sessionFactory);
        this.paymentService = new PaymentService(sessionFactory);
        this.filmService = new FilmService(sessionFactory);
    }

    public static void main(String[] args) {
        AppNew appNew = new AppNew(MySessionFactory.getSessionFactory());

        Customer customer = appNew.customerService.createCustomer();
        System.out.println(customer);

        appNew.rentalService.refundFilm();
        appNew.paymentService.setCustomerId((short) 600);
        appNew.paymentService.rentalOfInventory();

        Film film = appNew.filmService.newFilm();
        System.out.println(film);
    }
}
