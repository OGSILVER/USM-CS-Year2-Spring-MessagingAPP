package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.MessageDTO;
import dev.skirtty.webmessaging.models.Chats;
import dev.skirtty.webmessaging.models.Message;
import dev.skirtty.webmessaging.models.Users;
import dev.skirtty.webmessaging.repositories.ChatsRepository;
import dev.skirtty.webmessaging.repositories.MessageRepository;
import dev.skirtty.webmessaging.repositories.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private  final UserRepository userRepository;
    private  final ChatsRepository chatsRepository;

    public MessageDTO sendMessageByUser (MessageDTO messageDTO) {
        Message message = new Message();
        Users sender = userRepository.findById(messageDTO.getSenderId()).orElseThrow(() -> new RuntimeException("Nu exista asa id!"));
        Chats chat = chatsRepository.findById(messageDTO.getChatId()).orElseThrow(() -> new RuntimeException("Nu exista asa id la chat!"));

        message.setChat(chat);
        message.setSender(sender);
        message.setContent(messageDTO.getContent());
        message.setSent_at(LocalDateTime.now());

        messageRepository.save(message);

        return messageDTO;
    }
}
