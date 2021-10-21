package br.com.loris.culturalapi.dto;

import br.com.loris.culturalapi.enums.CulturalProductType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CulturalGenreDTO {
    @ApiModelProperty(value = "Cultural genre identity (ID)")
    private long id;

    @ApiModelProperty(value = "Type of cultural genre of this company")
    @Enumerated(EnumType.ORDINAL)
    private CulturalProductType type;

    @ApiModelProperty(value = "Name of cultural genre")
    @NotEmpty
    @Size(min = 5, max = 100)
    private String name;
}