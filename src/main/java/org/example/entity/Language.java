package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "language", schema = "movie")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Byte id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_update")
    @UpdateTimestamp
    private Timestamp lastUpdate;
}
