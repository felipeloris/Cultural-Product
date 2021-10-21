package br.com.loris.culturalapi.controller;

import br.com.loris.culturalapi.dto.CulturalGenreDTO;
import br.com.loris.culturalapi.dto.CulturalProductDTO;
import br.com.loris.culturalapi.dto.FilterDTO;
import br.com.loris.culturalapi.dto.MessageResponseDTO;
import br.com.loris.culturalapi.exception.DataNotFoundException;
import br.com.loris.culturalapi.service.CulturalGenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Cultural Genre CRUD")
@RestController
@RequestMapping("/api/v1/culturalgenre")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CulturalGenreController {
    private CulturalGenreService service;

    @ApiOperation(value = "Create a new cultural genre")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@RequestBody @Valid CulturalGenreDTO dto) {
        return service.create(dto);
    }

    @ApiOperation(value = "List all cultural genres")
    @GetMapping
    public List<CulturalGenreDTO> listAll() {
        return service.listAll();
    }

    @ApiOperation(value = "Find by cultural genres using filter")
    @GetMapping("/{filter}")
    public Page<CulturalGenreDTO> findAllByFilter(@RequestBody FilterDTO filter,
                                                  @PageableDefault(page = 0, size = 20)
                                                  @SortDefault.SortDefaults({
                                                          @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                                                          @SortDefault(sort = "id", direction = Sort.Direction.ASC)
                                                  }) Pageable pageable) {
        return service.findAllByFilter(filter, pageable);
    }

    @ApiOperation(value = "Find by cultural genre using ID")
    @GetMapping("/{id}")
    public CulturalGenreDTO findById(@PathVariable long id) throws DataNotFoundException {
        return service.findById(id);
    }

    @ApiOperation(value = "Update cultural genre")
    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid CulturalGenreDTO dto) throws DataNotFoundException {
        return service.updateById(id, dto);
    }

    @ApiOperation(value = "Delete cultural genre")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws DataNotFoundException {
        service.delete(id);
    }
}
