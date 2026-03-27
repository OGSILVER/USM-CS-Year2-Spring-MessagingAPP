package dev.skirtty.webmessaging.controllers;

import dev.skirtty.webmessaging.dto.UserResponse;
import dev.skirtty.webmessaging.services.ChatMembersService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-members")
@RequiredArgsConstructor
public class ChatMembersController {

    private final ChatMembersService chatMembersService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getChatMembers(@PathVariable Long chatId) {
        return ResponseEntity.ok(chatMembersService.getMembersByChatId(chatId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> addMember(@PathVariable Long chatId, @PathVariable Long userId) {
        chatMembersService.addMember(chatId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long chatId, @PathVariable Long userId) {
        chatMembersService.removeMember(chatId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Boolean> isMember(@PathVariable Long chatId, @PathVariable Long userId) {
        return ResponseEntity.ok(chatMembersService.isMember(chatId, userId));
    }

}
