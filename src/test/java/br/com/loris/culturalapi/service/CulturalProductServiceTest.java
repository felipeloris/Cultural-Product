package br.com.loris.culturalapi.service;

import br.com.loris.culturalapi.dto.CulturalProductDTO;
import br.com.loris.culturalapi.dto.MessageResponseDTO;
import br.com.loris.culturalapi.entity.CulturalProduct;
import br.com.loris.culturalapi.exception.DataNotFoundException;
import br.com.loris.culturalapi.mapper.CulturalProductMapper;
import br.com.loris.culturalapi.repository.CulturalProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.loris.culturalapi.utils.CulturalProductUtils.createFakeDTO;
import static br.com.loris.culturalapi.utils.CulturalProductUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CulturalProductServiceTest {

    @Mock
    private CulturalProductRepository repository;

    @Mock
    private CulturalProductMapper mapper;

    @InjectMocks
    private CulturalProductService service;

    /* Test given 'CulturalProductDTO then return success saved message */
    @Test
    void testSaveSuccess() {
        CulturalProductDTO CulturalProductDTO = createFakeDTO();
        CulturalProduct expectedSavedCulturalProduct = createFakeEntity();

        when(mapper.toEntity(CulturalProductDTO)).thenReturn(expectedSavedCulturalProduct);
        when(repository.save(any(CulturalProduct.class))).thenReturn(expectedSavedCulturalProduct);

        MessageResponseDTO successMessage = service.create(CulturalProductDTO);

        assertEquals("Created cultural product with ID 1", successMessage.getMessage());
    }

    /* Test given a valid 'Cultural Product Id' then return this 'CulturalProduct' */
    @Test
    void testFindByIdSuccess() throws DataNotFoundException {
        CulturalProductDTO expectedCulturalProductDTO = createFakeDTO();
        CulturalProduct expectedSavedCulturalProduct = createFakeEntity();
        expectedCulturalProductDTO.setId(expectedSavedCulturalProduct.getId());

        when(repository.findById(expectedSavedCulturalProduct.getId())).thenReturn(Optional.of(expectedSavedCulturalProduct));
        when(mapper.toDTO(expectedSavedCulturalProduct)).thenReturn(expectedCulturalProductDTO);

        CulturalProductDTO culturalProductDTO = service.findById(expectedSavedCulturalProduct.getId());

        assertEquals(expectedCulturalProductDTO, culturalProductDTO);

        assertEquals(expectedSavedCulturalProduct.getId(), culturalProductDTO.getId());
        assertEquals(expectedSavedCulturalProduct.getTitle(), culturalProductDTO.getTitle());
    }

    /* Test given invalid 'Cultural Product Id' then throw exception */
    @Test
    void testFindByIdError() {
        Long invalidCulturalProductId = 1L;
        when(repository.findById(invalidCulturalProductId))
                .thenReturn(Optional.ofNullable(any(CulturalProduct.class)));

        assertThrows(DataNotFoundException.class, () -> service.findById(invalidCulturalProductId));
    }

    /* Test given 'No Data' then return all 'CulturalProductDTO' registered */
    @Test
    void testFindAllSuccess() {
        List<CulturalProduct> expectedRegisteredPeople = Collections.singletonList(createFakeEntity());
        CulturalProductDTO CulturalProductDTO = createFakeDTO();

        when(repository.findAll()).thenReturn(expectedRegisteredPeople);
        when(mapper.toDTO(any(CulturalProduct.class))).thenReturn(CulturalProductDTO);

        List<CulturalProductDTO> expectedPeopleDTOList = service.listAll();

        assertFalse(expectedPeopleDTOList.isEmpty());
        assertEquals(expectedPeopleDTOList.get(0).getId(), CulturalProductDTO.getId());
    }

    /* Test given valid 'Cultural Product Id' and update info then return success on update */
    @Test
    void testUpdateSuccess() throws DataNotFoundException {
        var updatedCulturalProductId = 2L;

        CulturalProductDTO updateCulturalProductDTORequest = createFakeDTO();
        updateCulturalProductDTORequest.setId(updatedCulturalProductId);
        updateCulturalProductDTORequest.setTitle("Updated");

        CulturalProduct expectedCulturalProductToUpdate = createFakeEntity();
        expectedCulturalProductToUpdate.setId(updatedCulturalProductId);

        CulturalProduct expectedCulturalProductUpdated = createFakeEntity();
        expectedCulturalProductUpdated.setId(updatedCulturalProductId);
        expectedCulturalProductToUpdate.setTitle(updateCulturalProductDTORequest.getTitle());

        when(repository.findById(updatedCulturalProductId)).thenReturn(Optional.of(expectedCulturalProductUpdated));
        when(mapper.toEntity(updateCulturalProductDTORequest)).thenReturn(expectedCulturalProductUpdated);
        when(repository.save(any(CulturalProduct.class))).thenReturn(expectedCulturalProductUpdated);

        MessageResponseDTO successMessage = service.updateById(updatedCulturalProductId, updateCulturalProductDTORequest);

        assertEquals("Updated cultural product with ID with ID 2", successMessage.getMessage());
    }

    /* Test given invalid 'Cultural Product Id' and update info then throw exception on update */
    @Test
    void testUpdateError() {
        var invalidCulturalProductId = 1L;

        CulturalProductDTO updateCulturalProductDTORequest = createFakeDTO();
        updateCulturalProductDTORequest.setId(invalidCulturalProductId);
        updateCulturalProductDTORequest.setTitle("Updated");

        when(repository.findById(invalidCulturalProductId))
                .thenReturn(Optional.ofNullable(any(CulturalProduct.class)));

        assertThrows(DataNotFoundException.class, () -> service.updateById(invalidCulturalProductId, updateCulturalProductDTORequest));
    }

    /* Test given valid 'Cultural Product Id' then return success on delete */
    @Test
    void testDeleteSuccess() throws DataNotFoundException {
        var deletedCulturalProductId = 1L;
        CulturalProduct expectedCulturalProductToDelete = createFakeEntity();

        when(repository.findById(deletedCulturalProductId)).thenReturn(Optional.of(expectedCulturalProductToDelete));
        service.delete(deletedCulturalProductId);

        verify(repository, times(1)).deleteById(deletedCulturalProductId);
    }

    /* Test given invalid 'Cultural Product Id' then return exception on delete */
    @Test
    void testDeleteError() {
        var invalidCulturalProductId = 1L;

        when(repository.findById(invalidCulturalProductId))
                .thenReturn(Optional.ofNullable(any(CulturalProduct.class)));

        assertThrows(DataNotFoundException.class, () -> service.delete(invalidCulturalProductId));
    }

}
