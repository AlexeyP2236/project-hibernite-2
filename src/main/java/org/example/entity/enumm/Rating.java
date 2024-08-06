package org.example.entity.enumm;

import lombok.Getter;
@Getter
public enum Rating {
    G("G"), PG("PG"), PG13("PG-13"), R("R"), NC17("NC-17");
    private final String ratingString;

    Rating(String rating) {
        this.ratingString = rating;
    }
}
