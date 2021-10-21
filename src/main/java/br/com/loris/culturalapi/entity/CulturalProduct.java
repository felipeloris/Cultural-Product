package br.com.loris.culturalapi.entity;

import br.com.loris.culturalapi.enums.CulturalProductType;
import br.com.loris.culturalapi.enums.converter.CulturalProductTypeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="cultural_product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CulturalProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated
    @Convert(converter = CulturalProductTypeConverter.class)
    @Column(nullable = false)
    private CulturalProductType type;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = true)
    private LocalDate releaseDate;

    @ManyToOne(cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    //@JsonManagedReference
    //@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.DETACH},
    @ManyToMany(cascade = CascadeType.DETACH,
                fetch = FetchType.LAZY)
    @JoinTable(name = "cultural_product_genre",
               joinColumns = { @JoinColumn(name = "culturalProductId", nullable = false, updatable = false) },
               inverseJoinColumns = { @JoinColumn(name = "culturalProductGenreId", nullable = false, updatable = false)}
    )
    private List<CulturalGenre> culturalGenres;
}
