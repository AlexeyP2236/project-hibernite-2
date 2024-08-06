package org.example.dao;

import org.example.entity.Staff;
import org.hibernate.SessionFactory;

public class StaffDAO extends GenericDAO<Staff> {
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }

    public Staff getById(final byte id){
        return getCurrentSession().get(Staff.class, id);
    }
}
