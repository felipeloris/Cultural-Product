package br.com.loris.culturalapi.mapper;

import br.com.loris.culturalapi.dto.CompanyDTO;
import br.com.loris.culturalapi.dto.CulturalProductDTO;
import br.com.loris.culturalapi.entity.Company;
import br.com.loris.culturalapi.entity.CulturalProduct;
import br.com.loris.culturalapi.utils.CulturalProductUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CulturalProductMapperTest {

    @Autowired
    private CulturalProductMapper mapper;

    /* Test given 'CulturalProductDTO' then return 'CulturalProduct' */
    @Test
    void testDtoToEntitySuccess() {
        CulturalProductDTO dto = CulturalProductUtils.createFakeDTO();
        CulturalProduct entity = mapper.toEntity(dto);

        assertEquals(dto.getTitle(), entity.getTitle());
        assertEquals(dto.getReleaseDate(), entity.getReleaseDate());

        Company company = entity.getCompany();
        CompanyDTO companyDTO = dto.getCompany();

        assertEquals(companyDTO.getType(), company.getType());
        assertEquals(companyDTO.getName(), company.getName());
    }

    /* Test given 'CulturalProduct' then return 'CulturalProductDTO' */
    @Test
    void testEntityToDtoSuccess() {
        CulturalProduct entity = CulturalProductUtils.createFakeEntity();
        CulturalProductDTO dto = mapper.toDTO(entity);

        assertEquals(entity.getTitle(), dto.getTitle());
        assertEquals(entity.getReleaseDate(), dto.getReleaseDate());

        Company company = entity.getCompany();
        CompanyDTO companyDTO = dto.getCompany();

        assertEquals(company.getType(), companyDTO.getType());
        assertEquals(company.getName(), companyDTO.getName());
    }
}
