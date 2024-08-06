package org.example.entity.enumm;

import lombok.Getter;

import static java.util.Objects.isNull;

@Getter
public enum Features {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String string;
    Features(String string) {
        this.string = string;
    }

    public static Features getFeaturesByString(String string){
        if (isNull(string) || string.isEmpty()) return null;

        Features[] features = Features.values();
        for (Features feature : features) {
            if(feature.string.equals(string)){
                return feature;
            }
        }
        return null;
    }
}
