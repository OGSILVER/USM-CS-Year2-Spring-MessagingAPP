package md.universitate.proiect.client_server_chat.service.impl;


import md.universitate.proiect.client_server_chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceimpl {
    @Autowired
    private ChatRepository chatRepository;
}
