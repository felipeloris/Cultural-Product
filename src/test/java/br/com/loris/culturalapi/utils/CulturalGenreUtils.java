package br.com.loris.culturalapi.utils;

import br.com.loris.culturalapi.dto.CulturalGenreDTO;
import br.com.loris.culturalapi.entity.CulturalGenre;
import br.com.loris.culturalapi.enums.CulturalProductType;

public class CulturalGenreUtils {
    private static final CulturalProductType TYPE = CulturalProductType.MOVIE;
    private static final String NAME = "Aventura";

    public static CulturalGenreDTO createFakeDTO() {
        return CulturalGenreDTO.builder()
                .type(TYPE)
                .name(NAME)
                .build();
    }

    public static CulturalGenre createFakeEntity() {
        return CulturalGenre.builder()
                .type(TYPE)
                .name(NAME)
                .build();
    }
}
