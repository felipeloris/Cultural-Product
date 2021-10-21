package br.com.loris.culturalapi.utils;

import br.com.loris.culturalapi.dto.CulturalGenreDTO;
import br.com.loris.culturalapi.dto.CulturalProductDTO;
import br.com.loris.culturalapi.entity.CulturalGenre;
import br.com.loris.culturalapi.entity.CulturalProduct;
import br.com.loris.culturalapi.enums.CulturalProductType;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CulturalProductUtils {
    private static final CulturalProductType TYPE = CulturalProductType.MOVIE;
    private static final String TITLE = "Venom: Tempo de Carnificina";
    private static final String RELEASE_DATE = "2021-10-07";

    public static CulturalProductDTO createFakeDTO() {
        return CulturalProductDTO.builder()
                .type(TYPE)
                .title(TITLE)
                .releaseDate(RELEASE_DATE)
                .company(CompanyUtils.createFakeDTO())
                .culturalGenres(List.of(new CulturalGenreDTO[]{CulturalGenreUtils.createFakeDTO()}))
                .build();
    }

    public static CulturalProduct createFakeEntity() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(RELEASE_DATE, formatter);

        return CulturalProduct.builder()
                .type(TYPE)
                .title(TITLE)
                .releaseDate(localDate)
                .company(CompanyUtils.createFakeEntity())
                .culturalGenres(List.of(new CulturalGenre[]{CulturalGenreUtils.createFakeEntity()}))
                .build();
    }

    public static String asJsonString(CulturalProductDTO dto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
