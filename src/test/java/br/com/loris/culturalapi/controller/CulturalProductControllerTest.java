package br.com.loris.culturalapi.controller;

import br.com.loris.culturalapi.dto.CulturalProductDTO;
import br.com.loris.culturalapi.dto.MessageResponseDTO;
import br.com.loris.culturalapi.service.CulturalProductService;
import br.com.loris.culturalapi.utils.Utils;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;
import java.util.List;

import static br.com.loris.culturalapi.utils.CulturalProductUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static br.com.loris.culturalapi.utils.CulturalProductUtils.createFakeDTO;

@ExtendWith(MockitoExtension.class)
public class CulturalProductControllerTest {
    private static final String API_URL_PATH = "/api/v1/culturalproduct";

    private MockMvc mockMvc;

    private CulturalProductController controller;

    @Mock
    private CulturalProductService service;

    @BeforeEach
    void setUp() {
        controller = new CulturalProductController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    /* Test when 'create' (post) is called then a 'cultural product' should be created */
    @Test
    void testCreateSuccess() throws Exception {
        CulturalProductDTO expectedDTO = createFakeDTO();
        MessageResponseDTO expectedResponseMessage = Utils.createMessageResponse("Created cultural product with ID ", 1L);

        when(service.create(expectedDTO)).thenReturn(expectedResponseMessage);

        mockMvc.perform(post(API_URL_PATH)
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(expectedDTO)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.message", is(expectedResponseMessage.getMessage())));
    }

    /* Test when 'find by id' (get with ID parameter) is called then a 'cultural product' should be returned */
    @Test
    void testFindByIdSuccess() throws Exception {
        var expectedValidId = 1L;

        CulturalProductDTO expectedDTO = createFakeDTO();
        expectedDTO.setId(expectedValidId);

        when(service.findById(expectedValidId)).thenReturn(expectedDTO);

        mockMvc.perform(get(API_URL_PATH + "/" + expectedValidId)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.title", is("Venom: Tempo de Carnificina")))
               .andExpect(jsonPath("$.releaseDate", is("2021-10-07")));
    }

    /* Test when 'find by id' (get with ID parameter) is called then a 'error message' should be returned */
    @Test
    void testFindByIdError() throws Exception {
        var expectedValidId = 1L;
        CulturalProductDTO expectedDTO = createFakeDTO();
        expectedDTO.setId(expectedValidId);

        when(service.findById(expectedValidId)).thenThrow(NotFoundException.class);

        mockMvc.perform(get(API_URL_PATH + "/" + expectedValidId)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }

    /* Test when 'list all' (get) is called then a 'cultural product list' should be returned */
    @Test
    void testListAllSuccess() throws Exception {
        var expectedValidId = 1L;

        CulturalProductDTO expectedDTO = createFakeDTO();
        expectedDTO.setId(expectedValidId);
        List<CulturalProductDTO> expectedDTOList = Collections.singletonList(expectedDTO);

        when(service.listAll()).thenReturn(expectedDTOList);

        mockMvc.perform(get(API_URL_PATH)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id", is(1)))
               .andExpect(jsonPath("$[0].title", is("Venom: Tempo de Carnificina")))
               .andExpect(jsonPath("$[0].releaseDate", is("2021-10-07")));
    }

    /* Test when 'update by id' (put) is called then a 'cultural product' should be updated */
    @Test
    void testUpdateByIdSuccess() throws Exception {
        var expectedValidId = 1L;
        CulturalProductDTO expectedDTO = createFakeDTO();
        MessageResponseDTO expectedResponseMessage = Utils.createMessageResponse("Updated cultural product with ID ", expectedValidId);

        when(service.updateById(expectedValidId, expectedDTO)).thenReturn(expectedResponseMessage);

        mockMvc.perform(put(API_URL_PATH + "/" + expectedValidId)
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(expectedDTO)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message", is(expectedResponseMessage.getMessage())));
    }

    /* Test when 'delete by id' (delete) is called then a 'cultural product' should be deleted */
    @Test
    void testWhenDELETEIsCalledThenAPersonShouldBeDeleted() throws Exception {
        var expectedValidId = 1L;

        mockMvc.perform(delete(API_URL_PATH + "/" + expectedValidId)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNoContent());
    }

}
