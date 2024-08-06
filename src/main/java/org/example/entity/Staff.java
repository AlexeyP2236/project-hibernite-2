package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "staff", schema = "movie")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Byte id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "picture")
    private byte[] picture; //null
    @Column(name = "email")
    private String email; //null
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @Column(name = "active", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
