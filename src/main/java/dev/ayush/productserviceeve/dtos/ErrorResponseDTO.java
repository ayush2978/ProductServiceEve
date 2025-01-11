package dev.ayush.productserviceeve.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class ErrorResponseDTO {
    private String message;
    private int status;
}
