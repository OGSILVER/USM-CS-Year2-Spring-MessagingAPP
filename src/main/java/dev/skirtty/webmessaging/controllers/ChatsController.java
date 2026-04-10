package dev.skirtty.webmessaging.controllers;

import dev.skirtty.webmessaging.dto.ChatsDTO;
import dev.skirtty.webmessaging.dto.MessageDTO;
import dev.skirtty.webmessaging.dto.MessageRequestDTO;
import dev.skirtty.webmessaging.exceptions.ResourceExistsException;
import dev.skirtty.webmessaging.exceptions.ResourceNotFoundException;
import dev.skirtty.webmessaging.services.ChatsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatsController {

    public ChatsService chatsService;

    @PostMapping("/create/")
    public ResponseEntity<ChatsDTO> createChat (@Valid @RequestBody ChatsDTO chatsDTO) {
        boolean bool = chatsService.checkIfConversationExists(chatsDTO);

        if (bool) {
            throw new ResourceExistsException("Deja exista un chat cu acesti membrii");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(chatsService.createConversation(chatsDTO));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteChat (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(chatsService.deleteConversation(id));
    }

    @GetMapping("/messages/")
    public ResponseEntity<Page<MessageDTO>> getChatMessages (@Valid @RequestBody MessageRequestDTO messageRequestDTO) {
        return ResponseEntity.status(HttpStatus.FOUND).body(chatsService.getChatMessages(messageRequestDTO));
    }

}
