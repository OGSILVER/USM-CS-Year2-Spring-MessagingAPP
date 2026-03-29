package dev.skirtty.webmessaging.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatsDTO {

    private Long chatId;

    @NotBlank(message = "Nu ai pus un chat name!")
    private String name;

    @NotBlank(message = "Nu ai pus un chat type!")
    private String type;

    @NotBlank(message = "Nu ai pus o poza!")
    private String picture;

    private Long firstUserId;
    private Long secondUserId;
}
