package dev.skirtty.webmessaging.dto;

import dev.skirtty.webmessaging.models.Chats;
import dev.skirtty.webmessaging.models.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class MessageDTO {

    @NotBlank(message = "Nu ai pus conetent la mesaj!")
    private String content;

    @NotNull(message = "Nu are un sender!")
    private Long senderId;

    @NotNull(message = "Nu are un chat!")
    private Long chatId;

    private LocalDateTime sentAt;

}
