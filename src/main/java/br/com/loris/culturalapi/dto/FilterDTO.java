package br.com.loris.culturalapi.dto;

import br.com.loris.culturalapi.enums.QueryOperator;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterDTO {
    @ApiModelProperty(value = "Field to filter")
    private String field;

    @ApiModelProperty(value = "Operator that use in the filter")
    private QueryOperator operator;

    @ApiModelProperty(value = "Value to filter")
    private String value;

    @ApiModelProperty(value = "Value(s) to filter, used in case of IN operator")
    private List<String> values;
}
