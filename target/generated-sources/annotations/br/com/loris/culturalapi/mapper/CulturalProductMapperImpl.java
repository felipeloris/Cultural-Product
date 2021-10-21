package br.com.loris.culturalapi.mapper;

import br.com.loris.culturalapi.dto.CompanyDTO;
import br.com.loris.culturalapi.dto.CulturalGenreDTO;
import br.com.loris.culturalapi.dto.CulturalProductDTO;
import br.com.loris.culturalapi.entity.Company;
import br.com.loris.culturalapi.entity.CulturalGenre;
import br.com.loris.culturalapi.entity.CulturalProduct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-21T18:18:01-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
public class CulturalProductMapperImpl implements CulturalProductMapper {

    @Override
    public CulturalProduct toEntity(CulturalProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CulturalProduct culturalProduct = new CulturalProduct();

        if ( dto.getReleaseDate() != null ) {
            culturalProduct.setReleaseDate( LocalDate.parse( dto.getReleaseDate(), DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) ) );
        }
        culturalProduct.setId( dto.getId() );
        culturalProduct.setType( dto.getType() );
        culturalProduct.setTitle( dto.getTitle() );
        culturalProduct.setCompany( companyDTOToCompany( dto.getCompany() ) );
        culturalProduct.setCulturalGenres( culturalGenreDTOListToCulturalGenreList( dto.getCulturalGenres() ) );

        return culturalProduct;
    }

    @Override
    public CulturalProductDTO toDTO(CulturalProduct entity) {
        if ( entity == null ) {
            return null;
        }

        CulturalProductDTO culturalProductDTO = new CulturalProductDTO();

        culturalProductDTO.setId( entity.getId() );
        culturalProductDTO.setType( entity.getType() );
        culturalProductDTO.setTitle( entity.getTitle() );
        if ( entity.getReleaseDate() != null ) {
            culturalProductDTO.setReleaseDate( DateTimeFormatter.ISO_LOCAL_DATE.format( entity.getReleaseDate() ) );
        }
        culturalProductDTO.setCompany( companyToCompanyDTO( entity.getCompany() ) );
        culturalProductDTO.setCulturalGenres( culturalGenreListToCulturalGenreDTOList( entity.getCulturalGenres() ) );

        return culturalProductDTO;
    }

    protected Company companyDTOToCompany(CompanyDTO companyDTO) {
        if ( companyDTO == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( companyDTO.getId() );
        company.setType( companyDTO.getType() );
        company.setName( companyDTO.getName() );

        return company;
    }

    protected CulturalGenre culturalGenreDTOToCulturalGenre(CulturalGenreDTO culturalGenreDTO) {
        if ( culturalGenreDTO == null ) {
            return null;
        }

        CulturalGenre culturalGenre = new CulturalGenre();

        culturalGenre.setId( culturalGenreDTO.getId() );
        culturalGenre.setType( culturalGenreDTO.getType() );
        culturalGenre.setName( culturalGenreDTO.getName() );

        return culturalGenre;
    }

    protected List<CulturalGenre> culturalGenreDTOListToCulturalGenreList(List<CulturalGenreDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<CulturalGenre> list1 = new ArrayList<CulturalGenre>( list.size() );
        for ( CulturalGenreDTO culturalGenreDTO : list ) {
            list1.add( culturalGenreDTOToCulturalGenre( culturalGenreDTO ) );
        }

        return list1;
    }

    protected CompanyDTO companyToCompanyDTO(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setId( company.getId() );
        companyDTO.setType( company.getType() );
        companyDTO.setName( company.getName() );

        return companyDTO;
    }

    protected CulturalGenreDTO culturalGenreToCulturalGenreDTO(CulturalGenre culturalGenre) {
        if ( culturalGenre == null ) {
            return null;
        }

        CulturalGenreDTO culturalGenreDTO = new CulturalGenreDTO();

        culturalGenreDTO.setId( culturalGenre.getId() );
        culturalGenreDTO.setType( culturalGenre.getType() );
        culturalGenreDTO.setName( culturalGenre.getName() );

        return culturalGenreDTO;
    }

    protected List<CulturalGenreDTO> culturalGenreListToCulturalGenreDTOList(List<CulturalGenre> list) {
        if ( list == null ) {
            return null;
        }

        List<CulturalGenreDTO> list1 = new ArrayList<CulturalGenreDTO>( list.size() );
        for ( CulturalGenre culturalGenre : list ) {
            list1.add( culturalGenreToCulturalGenreDTO( culturalGenre ) );
        }

        return list1;
    }
}
