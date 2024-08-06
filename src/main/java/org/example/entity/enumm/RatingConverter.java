package org.example.entity.enumm;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        return attribute.getRatingString();
    }
    @Override
    public Rating convertToEntityAttribute(String dbData) {
        Rating[] ratings = Rating.values();
        for (Rating rating : ratings) {
            if (rating.getRatingString().equals(dbData)) {
                return rating;
            }
        }

        return null;
    }
}
