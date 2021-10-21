package br.com.loris.culturalapi.dto;

import br.com.loris.culturalapi.entity.Company;
import br.com.loris.culturalapi.entity.CulturalGenre;
import br.com.loris.culturalapi.enums.CulturalProductType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CulturalProductDTO {
    @ApiModelProperty(value = "Cultural product identity (ID)")
    private long id;

    @ApiModelProperty(value = "Type of cultural product of this company")
    @Enumerated(EnumType.ORDINAL)
    private CulturalProductType type;

    @ApiModelProperty(value = "Title of cultural product")
    @NotEmpty
    @Size(min = 5, max = 100)
    private String title;

    @ApiModelProperty(value = "Release date of cultural product - yyyy-MM-dd")
    private String  releaseDate;

    @ApiModelProperty(value = "Company (publisher/production companies) of cultural product")
    @Valid
    private CompanyDTO company;

    @ApiModelProperty(value = "Genres list of cultural product")
    @Valid
    private List<CulturalGenreDTO> culturalGenres;
}
