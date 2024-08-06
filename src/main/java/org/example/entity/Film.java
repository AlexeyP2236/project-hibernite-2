package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.enumm.Features;
import org.example.entity.enumm.Rating;
import org.example.entity.enumm.RatingConverter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Entity
@Getter
@Setter
@Table(schema = "movie", name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;
    @Column(name = "release_year", columnDefinition = "year")
    private Short releaseYear;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguageId;
    @Column(name = "rental_duration")
    private Byte rentalDuration;
    @Column(name = "rental_rate")
    private BigDecimal rentalRate;
    @Column(name = "length")
    private Short length;
    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;
    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    @Convert(converter = RatingConverter.class)
    private Rating rating;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeaturesString;

    public Set<Features> getSpecialFeatures() {
        if (isNull(specialFeaturesString) || specialFeaturesString.isEmpty())
            return Collections.emptySet();
        else {
            Set<Features> result = new HashSet<>();
            String[] features = specialFeaturesString.split(",");
            for (String feature : features) {
                result.add(Features.getFeaturesByString(feature));
            }
            result.remove(null);
            return result;
        }
    }

    public void setSpecialFeatures(Set<Features> specialFeatures) {
        if (isNull(specialFeatures))
            specialFeaturesString = null;
        else specialFeaturesString = specialFeatures.stream().map(Features::getString).collect(Collectors.joining(","));

    }

    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private List<Actor> actors = new ArrayList<>();

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", language=" + language +
                ", originalLanguageId=" + originalLanguageId +
                ", rentalDuration=" + rentalDuration +
                ", rentalRate=" + rentalRate +
                ", length=" + length +
                ", replacementCost=" + replacementCost +
                ", rating=" + rating +
                ", specialFeaturesString='" + specialFeaturesString + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", categories=" + categories +
                ", actors=" + actors +
                '}';
    }
}

