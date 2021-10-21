package br.com.loris.culturalapi.dto;

import br.com.loris.culturalapi.enums.CulturalProductType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    @ApiModelProperty(value = "Company  identity (ID)")
    private long id;

    @ApiModelProperty(value = "Type of cultural product of this company")
    @Enumerated(EnumType.ORDINAL)
    private CulturalProductType type;

    @ApiModelProperty(value = "Name of company")
    @NotEmpty
    @Size(min = 5, max = 100)
    private String name;
}