package br.com.loris.culturalapi.mapper;

import br.com.loris.culturalapi.dto.CulturalGenreDTO;
import br.com.loris.culturalapi.entity.CulturalGenre;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-21T18:18:01-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
public class CulturalGenreMapperImpl implements CulturalGenreMapper {

    @Override
    public CulturalGenre toEntity(CulturalGenreDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CulturalGenre culturalGenre = new CulturalGenre();

        culturalGenre.setId( dto.getId() );
        culturalGenre.setType( dto.getType() );
        culturalGenre.setName( dto.getName() );

        return culturalGenre;
    }

    @Override
    public CulturalGenreDTO toDTO(CulturalGenre entity) {
        if ( entity == null ) {
            return null;
        }

        CulturalGenreDTO culturalGenreDTO = new CulturalGenreDTO();

        culturalGenreDTO.setId( entity.getId() );
        culturalGenreDTO.setType( entity.getType() );
        culturalGenreDTO.setName( entity.getName() );

        return culturalGenreDTO;
    }
}
