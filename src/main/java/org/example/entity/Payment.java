package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "payment", schema = "movie")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Short id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "payment_date")
    private Date paymentDate;
    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
