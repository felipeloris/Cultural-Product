package br.com.loris.culturalapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {
    @ApiModelProperty(value = "Message from business layer")
    private String message;
}
