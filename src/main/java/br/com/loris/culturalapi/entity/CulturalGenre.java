package br.com.loris.culturalapi.entity;

import br.com.loris.culturalapi.enums.CulturalProductType;
import br.com.loris.culturalapi.enums.converter.CulturalProductTypeConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cultural_genre")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CulturalGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated
    @Convert(converter = CulturalProductTypeConverter.class)
    @Column(nullable = false)
    private CulturalProductType type;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToMany(mappedBy = "culturalGenres",
                fetch = FetchType.LAZY,
                cascade = CascadeType.DETACH
    )
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<CulturalProduct> culturalProducts;
}
