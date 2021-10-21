package br.com.loris.culturalapi.mapper;

import br.com.loris.culturalapi.dto.CompanyDTO;
import br.com.loris.culturalapi.entity.Company;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-21T18:18:01-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public Company toEntity(CompanyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( dto.getId() );
        company.setType( dto.getType() );
        company.setName( dto.getName() );

        return company;
    }

    @Override
    public CompanyDTO toDTO(Company entity) {
        if ( entity == null ) {
            return null;
        }

        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setId( entity.getId() );
        companyDTO.setType( entity.getType() );
        companyDTO.setName( entity.getName() );

        return companyDTO;
    }
}
