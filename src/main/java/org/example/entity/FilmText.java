package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@Table(schema = "movie", name = "film_text")
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Short film_id;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;
    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;
}
