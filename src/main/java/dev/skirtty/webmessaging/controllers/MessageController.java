package dev.skirtty.webmessaging.controllers;

import dev.skirtty.webmessaging.dto.MessageDTO;
import dev.skirtty.webmessaging.services.MessageService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<MessageDTO> sendMessage (@Valid @RequestBody MessageDTO messageDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendMessageByUser(messageDTO));
    }
}
