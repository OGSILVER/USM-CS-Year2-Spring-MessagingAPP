package dev.skirtty.webmessaging.dto;

import jakarta.validation.constraints.NotNull;

public class ChatsRequestDTO {

    @NotNull(message = "Nu ai pus id la chat!")
    private Long chatId;

    @NotNull
    private int page;

    @NotNull
    private int size;
}
