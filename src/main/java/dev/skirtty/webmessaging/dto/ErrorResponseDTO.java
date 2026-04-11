package dev.skirtty.webmessaging.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ErrorResponseDTO {

    private int status;
    private String content;

    @CreationTimestamp
    private LocalDateTime timestamp;

    public ErrorResponseDTO(int status, String content) {
        this.status = status;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }
}
