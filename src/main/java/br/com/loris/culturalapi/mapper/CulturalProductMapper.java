package br.com.loris.culturalapi.mapper;

import br.com.loris.culturalapi.dto.CulturalProductDTO;
import br.com.loris.culturalapi.entity.CulturalProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CulturalProductMapper {
    CulturalProductMapper INSTANCE = Mappers.getMapper(CulturalProductMapper.class);

    @Mapping(target = "releaseDate", source = "releaseDate", dateFormat = "yyyy-MM-dd")
    CulturalProduct toEntity(CulturalProductDTO dto);

    CulturalProductDTO toDTO(CulturalProduct entity);
}