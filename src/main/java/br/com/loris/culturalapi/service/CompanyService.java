package br.com.loris.culturalapi.service;

import br.com.loris.culturalapi.dto.CompanyDTO;
import br.com.loris.culturalapi.dto.FilterDTO;
import br.com.loris.culturalapi.dto.MessageResponseDTO;
import br.com.loris.culturalapi.entity.Company;
import br.com.loris.culturalapi.enums.CulturalProductType;
import br.com.loris.culturalapi.exception.DataNotFoundException;
import br.com.loris.culturalapi.helper.FilterHelper;
import br.com.loris.culturalapi.mapper.CompanyMapper;
import br.com.loris.culturalapi.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyService {
    private CompanyRepository repository;
    private final CompanyMapper mapper = CompanyMapper.INSTANCE;
    private final FilterHelper<Company> filterHelper = new FilterHelper();

    @Transactional(readOnly = false)
    public void populateDB() {
        // Producers
        repository.save(new Company(0, CulturalProductType.MOVIE, "Sony Pictures Entertainment", null));
        repository.save(new Company(0, CulturalProductType.MOVIE, "The Walt Disney Company", null));
        repository.save(new Company(0, CulturalProductType.MOVIE, "The Warner Brothers", null));
        repository.save(new Company(0, CulturalProductType.MOVIE, "Universal Pictures", null));
        repository.save(new Company(0, CulturalProductType.MOVIE, "21st Century Fox", null));
        repository.save(new Company(0, CulturalProductType.MOVIE, "Paramount Motion Pictures Group", null));
        repository.save(new Company(0, CulturalProductType.MOVIE, "Lions Gate Entertainment", null));
        repository.save(new Company(0, CulturalProductType.MOVIE, "The Weinstein Company", null));
        repository.save(new Company(0, CulturalProductType.MOVIE, "MGM (Metro Goldwyn Mayer)", null));

        // Records (music publishing)
        repository.save(new Company(0, CulturalProductType.MUSIC, "Sony Music", null));
        repository.save(new Company(0, CulturalProductType.MUSIC, "Disney Music", null));
        repository.save(new Company(0, CulturalProductType.MUSIC, "Warner Music", null));
        repository.save(new Company(0, CulturalProductType.MUSIC, "Universal Music", null));
        repository.save(new Company(0, CulturalProductType.MUSIC, "EMI", null));
        repository.save(new Company(0, CulturalProductType.MUSIC, "Som Livre", null));

        // Publishers (book publishing)
        repository.save(new Company(0, CulturalProductType.BOOK, "Disney Publishing Worldwide", null));
        repository.save(new Company(0, CulturalProductType.BOOK, "Workman", null));
        repository.save(new Company(0, CulturalProductType.BOOK, "Sterling", null));
        repository.save(new Company(0, CulturalProductType.BOOK, "Macmillan", null));
        repository.save(new Company(0, CulturalProductType.BOOK, "Penguin Random House", null));
        repository.save(new Company(0, CulturalProductType.BOOK, "Companhia das Letras", null));
        repository.save(new Company(0, CulturalProductType.BOOK, "Novatec Editora", null));
        repository.save(new Company(0, CulturalProductType.BOOK, "Editora Moderna", null));
        repository.save(new Company(0, CulturalProductType.BOOK, "Editora Ftd", null));
    }

    public MessageResponseDTO create(CompanyDTO dto) {
        Company entityToSave = mapper.toEntity(dto);
        Company savedModel = repository.save(entityToSave);
        
        return createMessageResponse(savedModel.getId(), "Created cultural genre with ID ");
    }

    public Page<CompanyDTO> findAllByFilter(FilterDTO filter, Pageable pageable){
        Specification<Company> spec = filterHelper.createSpecification(filter);

        Page<Company> pageEntity = repository.findAll(spec, pageable);

        return pageEntity.map(mapper::toDTO);
    }

    public List<CompanyDTO> listAll() {
        List<Company> allEntities = repository.findAll();
        
        return allEntities.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public CompanyDTO findById(Long id) throws DataNotFoundException {
        Company entity = verifyIfEntityExists(id);
        
        return mapper.toDTO(entity);
    }

    public void delete(Long id) throws DataNotFoundException {
        verifyIfEntityExists(id);
        
        repository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, CompanyDTO dto) throws DataNotFoundException {
        verifyIfEntityExists(id);
        Company entityToUpdate = mapper.toEntity(dto);
        repository.save(entityToUpdate);

        return createMessageResponse(id, "Updated cultural genre with ID ");
    }

    private Company verifyIfEntityExists(Long id) throws DataNotFoundException {
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
