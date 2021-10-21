package br.com.loris.culturalapi.controller;

import br.com.loris.culturalapi.dto.CompanyDTO;
import br.com.loris.culturalapi.dto.FilterDTO;
import br.com.loris.culturalapi.dto.MessageResponseDTO;
import br.com.loris.culturalapi.exception.DataNotFoundException;
import br.com.loris.culturalapi.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(value = "Company CRUD")
@RestController
@RequestMapping("/api/v1/company")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyController {
    private CompanyService service;

    @ApiOperation(value = "Create a new company")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@RequestBody @Valid CompanyDTO dto) {
        return service.create(dto);
    }

    @ApiOperation(value = "List all companies")
    @GetMapping
    public List<CompanyDTO> listAll() {
        return service.listAll();
    }

    @ApiOperation(value = "Find by companies using filter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the filtered list of societies"),
            @ApiResponse(code = 403, message = "You do not have permission to access this feature."),
    })
    @GetMapping("/{filter}")
    public Page<CompanyDTO> findAllByFilter(@RequestBody FilterDTO filter,
                                            @PageableDefault(page = 0, size = 20)
                                            @SortDefault.SortDefaults({
                                                    @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                                                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
                                            }) Pageable pageable) {
        return service.findAllByFilter(filter, pageable);
    }

    @ApiOperation(value = "Find by company using ID")
    @GetMapping("/{id}")
    public CompanyDTO findById(@PathVariable long id) throws DataNotFoundException {
        return service.findById(id);
    }

    @ApiOperation(value = "Update company")
    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid CompanyDTO dto) throws DataNotFoundException {
        return service.updateById(id, dto);
    }

    @ApiOperation(value = "Delete company")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws DataNotFoundException {
        service.delete(id);
    }
}
