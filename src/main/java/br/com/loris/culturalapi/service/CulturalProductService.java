package br.com.loris.culturalapi.service;

import br.com.loris.culturalapi.dto.CulturalProductDTO;
import br.com.loris.culturalapi.dto.FilterDTO;
import br.com.loris.culturalapi.dto.MessageResponseDTO;
import br.com.loris.culturalapi.entity.Company;
import br.com.loris.culturalapi.entity.CulturalGenre;
import br.com.loris.culturalapi.entity.CulturalProduct;
import br.com.loris.culturalapi.enums.CulturalProductType;
import br.com.loris.culturalapi.exception.DataNotFoundException;
import br.com.loris.culturalapi.helper.FilterHelper;
import br.com.loris.culturalapi.mapper.CulturalProductMapper;
import br.com.loris.culturalapi.repository.CulturalProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CulturalProductService {
    private CulturalProductRepository repository;
    private final CulturalProductMapper mapper = CulturalProductMapper.INSTANCE;
    private final FilterHelper<CulturalProduct> filterHelper = new FilterHelper();

    @Transactional(readOnly = false)
    public void populateDB() {
        var sony = new Company();
        sony.setId(1);

        var waltDisney = new Company();
        waltDisney.setId(2);

        var universal = new Company();
        universal.setId(3);

        var aventura = new CulturalGenre();
        aventura.setId(1);

        var ficcao = new CulturalGenre();
        ficcao.setId(2);

        var comedia = new CulturalGenre();
        comedia.setId(3);

        repository.save(new CulturalProduct(0, CulturalProductType.MOVIE, "Venom: Tempo de Carnificina",LocalDate.of(2021, 10, 07), sony, List.of(new CulturalGenre[]{aventura})));
        repository.save(new CulturalProduct(0, CulturalProductType.MOVIE, "Tron - Uma Odisseia Eletrônica",LocalDate.of(1982, 8, 12), waltDisney, List.of(new CulturalGenre[]{aventura, ficcao})));
        repository.save(new CulturalProduct(0, CulturalProductType.MOVIE, "Os Irmãos Cara-de-Pau", LocalDate.of(1980, 06, 20), universal, List.of(new CulturalGenre[]{aventura, comedia})));
    }

    public MessageResponseDTO create(CulturalProductDTO dto) {
        CulturalProduct entityToSave = mapper.toEntity(dto);
        CulturalProduct savedModel = repository.save(entityToSave);
        
        return createMessageResponse(savedModel.getId(), "Created cultural product with ID ");
    }

    public List<CulturalProductDTO> listAll() {
        List<CulturalProduct> allEntities = repository.findAll();
        
        return allEntities.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Page<CulturalProductDTO> findAllByFilter(FilterDTO filter, Pageable pageable){
        Specification<CulturalProduct> spec = filterHelper.createSpecification(filter);

        Page<CulturalProduct> pageEntity = repository.findAll(spec, pageable);

        //return pageEntity.map(entity ->  mapper.toDTO(entity));
        return pageEntity.map(mapper::toDTO);
    }

    public CulturalProductDTO findById(Long id) throws DataNotFoundException {
        CulturalProduct entity = verifyIfEntityExists(id);
        
        return mapper.toDTO(entity);
    }

    public void delete(Long id) throws DataNotFoundException {
        verifyIfEntityExists(id);
        
        repository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, CulturalProductDTO dto) throws DataNotFoundException {
        verifyIfEntityExists(id);
        CulturalProduct entityToUpdate = mapper.toEntity(dto);
        repository.save(entityToUpdate);

        return createMessageResponse(id, "Updated cultural product with ID ");
    }

    /*
    private Specification<CulturalProduct> nameLike(String name){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(CulturalProduct_.NAME), "%"+name+"%");
    }
    Specification<CulturalProduct> nameLike =
            (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get(CulturalProduct_.Title), "%"+name+"%");
    */

    private CulturalProduct verifyIfEntityExists(Long id) throws DataNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
