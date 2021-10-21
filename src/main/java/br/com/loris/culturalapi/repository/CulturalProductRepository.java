package br.com.loris.culturalapi.repository;

import br.com.loris.culturalapi.entity.CulturalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Component
@EnableJpaRepositories
public interface CulturalProductRepository extends JpaRepository<CulturalProduct, Long>,
        JpaSpecificationExecutor<CulturalProduct> {

    @Transactional(readOnly = true)
    @Query(value =  "SELECT *" +
                    " FROM cultural_product cp" +
                    " INNER JOIN cultural_product_genre cpg ON cpg.culturalProductId = cp.id" +
                    " INNER JOIN cultural_genre cg ON cg.id = cpg.culturalProductGenreId" +
                    " WHERE cg like '%?:genreName%'",
           nativeQuery = true)
    List<CulturalProduct> findByGenre(@Param("genreName") String genreName);
}
