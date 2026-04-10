package dev.skirtty.webmessaging.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequestDTO {

    @NotNull(message = "Trebuie sa specifici un chat id!")
    private Long chat_id;

    @NotNull(message = "Trebuie sa specifici o pagina!")
    private int page;

    @NotNull(message = "Trebuie sa specifici o marime per pagina!")
    private int size;
}
