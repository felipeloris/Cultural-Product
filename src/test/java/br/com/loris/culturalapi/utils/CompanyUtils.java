package br.com.loris.culturalapi.utils;

import br.com.loris.culturalapi.dto.CompanyDTO;
import br.com.loris.culturalapi.entity.Company;
import br.com.loris.culturalapi.enums.CulturalProductType;

public class CompanyUtils {
    private static final CulturalProductType TYPE = CulturalProductType.MOVIE;
    private static final String NAME = "Sony Pictures Entertainment";

    public static CompanyDTO createFakeDTO() {
        return CompanyDTO.builder()
                .type(TYPE)
                .name(NAME)
                .build();
    }

    public static Company createFakeEntity() {
        return Company.builder()
                .type(TYPE)
                .name(NAME)
                .build();
    }

}
