package dev.skirtty.webmessaging.controllers;

import dev.skirtty.webmessaging.dto.UsersDTO;
import dev.skirtty.webmessaging.services.ChatsMembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-members")
@RequiredArgsConstructor
public class ChatsMembersController {

    private final ChatsMembersService chatsMembersService;

    @GetMapping("/{chatId}")
    public ResponseEntity<List<UsersDTO>> getChatMembers(@PathVariable Long chatId) {
        return ResponseEntity.ok(chatsMembersService.getMembersByChatId(chatId));
    }

    @PostMapping("/{chatId}/{userId}")
    public ResponseEntity<Void> addMember(@PathVariable Long chatId, @PathVariable Long userId) {
        chatsMembersService.addMember(chatId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{chatId}/{userId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long chatId, @PathVariable Long userId) {
        chatsMembersService.removeMember(chatId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{chatId}/{userId}")
    public ResponseEntity<Boolean> isMember(@PathVariable Long chatId, @PathVariable Long userId) {
        return ResponseEntity.ok(chatsMembersService.isMember(chatId, userId));
    }

}
