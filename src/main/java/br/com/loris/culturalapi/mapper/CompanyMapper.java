package br.com.loris.culturalapi.mapper;

import br.com.loris.culturalapi.dto.CompanyDTO;
import br.com.loris.culturalapi.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company toEntity(CompanyDTO dto);

    CompanyDTO toDTO(Company entity);
}