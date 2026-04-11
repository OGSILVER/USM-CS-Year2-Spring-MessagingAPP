package dev.skirtty.webmessaging.dto;

import dev.skirtty.webmessaging.models.ChatsMembers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatsMembersDTO {

    private Long id;
    private Long chatId;
    private Long userId;
    private String username;

    public ChatsMembersDTO(ChatsMembers chatsMembers) {
        this.id = chatsMembers.getId();
        this.chatId = chatsMembers.getChat().getId();
        this.userId = chatsMembers.getUser().getId();
        this.username = chatsMembers.getUser().getUsername();
    }
}