package org.example.service;

import org.example.dao.AddressDAO;
import org.example.dao.CityDAO;
import org.example.dao.CustomerDAO;
import org.example.dao.StoreDAO;
import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CustomerService {

    private final SessionFactory sessionFactory;
    private final StoreDAO storeDAO;
    private final CityDAO cityDAO;
    private final AddressDAO addressDAO;
    private final CustomerDAO customerDAO;

    public CustomerService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        this.storeDAO = new StoreDAO(sessionFactory);
        this.cityDAO = new CityDAO(sessionFactory);
        this.addressDAO = new AddressDAO(sessionFactory);
        this.customerDAO = new CustomerDAO(sessionFactory);
    }

    public Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Store store = storeDAO.getById(Byte.valueOf("1"));

            Address address = new Address();
            address.setAddress("325 Topol Drive");
            address.setDistrict("Madhya Pradesh");
            address.setCity(cityDAO.getByName("Akron"));
            address.setPhone("4657282285570");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setAddress(address);
            customer.setActive(true);
            customer.setFirstName("Albert");
            customer.setLastName("Vishnevsky");
            customer.setStore(store);
            customerDAO.save(customer);

            session.getTransaction().commit();
            return customer;
        }
    }

}
