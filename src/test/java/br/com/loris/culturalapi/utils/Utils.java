package br.com.loris.culturalapi.utils;

import br.com.loris.culturalapi.dto.MessageResponseDTO;

public class Utils {
    public static MessageResponseDTO createMessageResponse(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }
}
