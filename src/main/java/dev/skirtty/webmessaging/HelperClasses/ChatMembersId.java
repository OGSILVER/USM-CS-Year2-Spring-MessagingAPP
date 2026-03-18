package dev.skirtty.webmessaging.HelperClasses;

import dev.skirtty.webmessaging.models.Chats;
import dev.skirtty.webmessaging.models.Users;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ChatMembersId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chats chat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
