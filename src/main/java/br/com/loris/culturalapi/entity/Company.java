package br.com.loris.culturalapi.entity;

import br.com.loris.culturalapi.enums.CulturalProductType;
import br.com.loris.culturalapi.enums.converter.CulturalProductTypeConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="company")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated
    @Convert(converter = CulturalProductTypeConverter.class)
    @Column(nullable = false)
    private CulturalProductType type;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "company",
                fetch = FetchType.LAZY,
                cascade = CascadeType.ALL
    )
    private List<CulturalProduct> culturalProducts;
}
