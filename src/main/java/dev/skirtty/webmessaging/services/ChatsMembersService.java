package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.UsersDTO;
import dev.skirtty.webmessaging.exceptions.ResourceExistsException;
import dev.skirtty.webmessaging.exceptions.ResourceIsNullException;
import dev.skirtty.webmessaging.exceptions.ResourceNotFoundException;
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
        if (chatId == null) {
            throw new ResourceIsNullException("Parametrul chatId este gol");
        }

        if (chatMembersRepository.findByChat_Id(chatId).isEmpty()) throw new ResourceNotFoundException("Nu exista asa chat id cu membrii!");
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
        if (chatId == null) {
            throw new ResourceIsNullException("Parametrul chatId este gol");
        }

        if (userId == null) {
            throw new ResourceIsNullException("Parametrul userId este gol");
        }

        if (chatMembersRepository.existsByChat_IdAndUser_Id(chatId, userId)) {
            throw new ResourceExistsException("Utilizatorul " + userId + " este deja membru al chat-ului " + chatId);
        }
        Chats chat = chatsRepository.findById(chatId)
                .orElseThrow(() -> new ResourceNotFoundException("Chat-ul nu a fost gasit: " + chatId));
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilizatorul nu a fost gasit: " + userId));

        ChatsMembers member = new ChatsMembers();
        member.setChat(chat);
        member.setUser(user);

        chatMembersRepository.save(member);
    }

    public void removeMember(Long chatId, Long userId) {
        if (chatId == null) {
            throw new ResourceIsNullException("Parametrul chatId este gol");
        }

        if (userId == null) {
            throw new ResourceIsNullException("Parametrul userId este gol");
        }
        chatMembersRepository.deleteByChat_IdAndUser_Id(chatId, userId);
    }

    public boolean isMember(Long chatId, Long userId) {
        if (chatId == null) {
            throw new ResourceIsNullException("Parametrul chatId este gol");
        }

        if (userId == null) {
            throw new ResourceIsNullException("Parametrul userId este gol");
        }
        return chatMembersRepository.existsByChat_IdAndUser_Id(chatId, userId);
    }
}
