package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "customer", schema = "movie")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    public Short id;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email; //null
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "active", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active;
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;
    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", store=" + store +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", active=" + active +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
