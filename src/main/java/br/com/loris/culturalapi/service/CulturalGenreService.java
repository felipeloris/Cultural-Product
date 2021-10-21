package br.com.loris.culturalapi.service;

import br.com.loris.culturalapi.dto.CulturalGenreDTO;
import br.com.loris.culturalapi.dto.CulturalProductDTO;
import br.com.loris.culturalapi.dto.FilterDTO;
import br.com.loris.culturalapi.dto.MessageResponseDTO;
import br.com.loris.culturalapi.entity.CulturalGenre;
import br.com.loris.culturalapi.entity.CulturalProduct;
import br.com.loris.culturalapi.enums.CulturalProductType;
import br.com.loris.culturalapi.exception.DataNotFoundException;
import br.com.loris.culturalapi.helper.FilterHelper;
import br.com.loris.culturalapi.mapper.CulturalGenreMapper;
import br.com.loris.culturalapi.repository.CulturalGenreRepository;
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
public class CulturalGenreService {
    private CulturalGenreRepository repository;
    private final CulturalGenreMapper mapper = CulturalGenreMapper.INSTANCE;
    private final FilterHelper<CulturalGenre> filterHelper = new FilterHelper();

    @Transactional(readOnly = false)
    public void populateDB() {
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Aventura", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Ficção", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Comédia", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Ação", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Desenho", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Romance", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Fantasia", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Drama", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Terror", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Suspense", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Policial", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MOVIE, "Documentário", null));

        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Aventura", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Comédia", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Ação", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Desenho", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Romance", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Fantasia", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Drama", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Terror", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Suspense", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Ficção", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Policial", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Romance", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Documentário", null));
        repository.save(new CulturalGenre(0, CulturalProductType.BOOK, "Biografia", null));

        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Rock", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Pop", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "New Age", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Clássico", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Axé", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Black Music", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Blues", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Bossa Nova", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Classic Rock", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Country", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Eletrônico", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Techno", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Chillout", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Dance", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Disco", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Electro Swing", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Fado", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Folk", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Forró", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Funk", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Gospel/Religioso", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Grunge", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Hard Rock", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Hardcore", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Heavy Metal", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Hip Hop", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "House", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Indie", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Industrial", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Instrumental", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Jazz", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Metal", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Punk Rock", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Psicodelia", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Progressivo", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Rap", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Reggae", null));
        repository.save(new CulturalGenre(0, CulturalProductType.MUSIC, "Samba", null));
    }

    public MessageResponseDTO create(CulturalGenreDTO dto) {
        CulturalGenre entityToSave = mapper.toEntity(dto);
        CulturalGenre savedModel = repository.save(entityToSave);
        
        return createMessageResponse(savedModel.getId(), "Created cultural genre with ID ");
    }

    public Page<CulturalGenreDTO> findAllByFilter(FilterDTO filter, Pageable pageable){
        Specification<CulturalGenre> spec = filterHelper.createSpecification(filter);

        Page<CulturalGenre> pageEntity = repository.findAll(spec, pageable);

        return pageEntity.map(mapper::toDTO);
    }

    public List<CulturalGenreDTO> listAll() {
        List<CulturalGenre> allEntities = repository.findAll();
        
        return allEntities.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public CulturalGenreDTO findById(Long id) throws DataNotFoundException {
        CulturalGenre entity = verifyIfEntityExists(id);
        
        return mapper.toDTO(entity);
    }

    public void delete(Long id) throws DataNotFoundException {
        verifyIfEntityExists(id);
        
        repository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, CulturalGenreDTO dto) throws DataNotFoundException {
        verifyIfEntityExists(id);
        CulturalGenre entityToUpdate = mapper.toEntity(dto);
        repository.save(entityToUpdate);

        return createMessageResponse(id, "Updated cultural genre with ID ");
    }

    private CulturalGenre verifyIfEntityExists(Long id) throws DataNotFoundException {
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
