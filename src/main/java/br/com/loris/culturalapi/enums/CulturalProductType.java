package br.com.loris.culturalapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CulturalProductType {
    UNDEFINED(0, null),
    BOOK(1, "Book"),
    MOVIE(2, "Movie"),
    MUSIC(3, "Music");

    private final Integer value;
    private final String description;

    /*
    CulturalProductType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }
    */
}
