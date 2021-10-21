package br.com.loris.culturalapi.mapper;

import br.com.loris.culturalapi.dto.CulturalGenreDTO;
import br.com.loris.culturalapi.entity.CulturalGenre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CulturalGenreMapper {
    CulturalGenreMapper INSTANCE = Mappers.getMapper(CulturalGenreMapper.class);

    CulturalGenre toEntity(CulturalGenreDTO dto);

    CulturalGenreDTO toDTO(CulturalGenre entity);
}