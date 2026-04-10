package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.ChatsDTO;
import dev.skirtty.webmessaging.dto.MessageDTO;
import dev.skirtty.webmessaging.dto.MessageRequestDTO;
import dev.skirtty.webmessaging.exceptions.ResourceNotFoundException;
import dev.skirtty.webmessaging.models.Chats;
import dev.skirtty.webmessaging.models.ChatsMembers;
import dev.skirtty.webmessaging.models.Message;
import dev.skirtty.webmessaging.repositories.ChatsMembersRepository;
import dev.skirtty.webmessaging.repositories.ChatsRepository;
import dev.skirtty.webmessaging.repositories.MessageRepository;
import dev.skirtty.webmessaging.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatsService {

    final private ChatsRepository chatsRepository;
    final private UsersRepository usersRepository;
    final private ChatsMembersRepository chatsMembersRepository;
    final private MessageRepository messageRepository;

    public ChatsDTO createConversation (ChatsDTO chatsDTO) {

        ChatsMembers firstChatMember = new ChatsMembers();
        ChatsMembers secondChatMember = new ChatsMembers();
        Chats chats = new Chats();

        chats.setName(chatsDTO.getName());
        chats.setPicture(chatsDTO.getPicture());
        chats.setType(chats.getType());

        firstChatMember.setChat(chats);
        firstChatMember.setUser(usersRepository.findById(chatsDTO.getFirstUserId()).orElseThrow(() -> new RuntimeException("Nu exista asa id!")));
        secondChatMember.setChat(chats);
        secondChatMember.setUser(usersRepository.findById(chatsDTO.getSecondUserId()).orElseThrow(() -> new RuntimeException("Nu exista asa id!")));

        chatsRepository.save(chats);
        chatsMembersRepository.save(firstChatMember);
        chatsMembersRepository.save(secondChatMember);

        chatsDTO.setChatId(chats.getId());
        return chatsDTO;
    }

    public boolean checkIfConversationExists (ChatsDTO chatsDTO) {

        ChatsMembers firstChatMember = new ChatsMembers();
        ChatsMembers secondChatMember = new ChatsMembers();

        if (chatsMembersRepository.existsByUsersId(chatsDTO.getFirstUserId(), chatsDTO.getSecondUserId())) {
            return true;
        } else {
            return false;
        }
    }

    public Long deleteConversation (Long id) {
        Chats chats = chatsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nu exista asa id de chat!"));
        chatsRepository.deleteById(id);
        return id;
    }

    public Page<MessageDTO> getChatMessages (MessageRequestDTO messageRequestDTO) {
        Pageable pageable = PageRequest.of(messageRequestDTO.getPage(), messageRequestDTO.getSize());
        Page<Message> messages = chatsRepository.findMessagesByChatId(messageRequestDTO.getChat_id(), pageable);

        // Convertesti fiecare Message -> MessageDTO
        return messages.map(message -> {
            MessageDTO dto = new MessageDTO();
            dto.setContent(message.getContent());
            dto.setSenderId(message.getSender().getId());
            dto.setChatId(message.getChat().getId());
            dto.setSentAt(message.getSent_at());
            return dto;
        });
    }
}
