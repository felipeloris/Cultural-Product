package br.com.loris.culturalapi.repository;

import br.com.loris.culturalapi.entity.CulturalGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
@EnableJpaRepositories
public interface CulturalGenreRepository extends JpaRepository<CulturalGenre, Long>,
        JpaSpecificationExecutor<CulturalGenre> {
}
