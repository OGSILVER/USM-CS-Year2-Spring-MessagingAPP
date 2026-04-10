package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.UsersDTO;
import dev.skirtty.webmessaging.models.Chats;
import dev.skirtty.webmessaging.models.ChatsMembers;
import dev.skirtty.webmessaging.models.Users;
import dev.skirtty.webmessaging.repositories.ChatsMembersRepository;
import dev.skirtty.webmessaging.repositories.ChatsRepository;
import dev.skirtty.webmessaging.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatsMembersService {

    private final ChatsMembersRepository chatMembersRepository;
    private final ChatsRepository chatsRepository;
    private final UsersRepository userRepository;

    public List<UsersDTO> getMembersByChatId(Long chatId) {
        return chatMembersRepository.findByChat_Id(chatId).stream()
                .map(member -> {
                    Users user = member.getUser();
                    UsersDTO response = new UsersDTO();
                    response.setId(user.getId());
                    response.setUsername(user.getUsername());
                    response.setEmail(user.getEmail());
                    response.setOnline(user.isOnline());
                    return response;
                }).toList();
    }

    public void addMember(Long chatId, Long userId) {
        Chats chat = chatsRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found: " + chatId));
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        ChatsMembers member = new ChatsMembers();
        member.setChat(chat);
        member.setUser(user);

        chatMembersRepository.save(member);
    }

    public void removeMember(Long chatId, Long userId) {
        chatMembersRepository.deleteByChat_IdAndUser_Id(chatId, userId);
    }

    public boolean isMember(Long chatId, Long userId) {
        return chatMembersRepository.existsByChat_IdAndUser_Id(chatId, userId);
    }
}
