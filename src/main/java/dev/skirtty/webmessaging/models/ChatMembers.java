package dev.skirtty.webmessaging.models;

import dev.skirtty.webmessaging.HelperClasses.ChatMembersId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chats_members")
@Getter
@Setter
@NoArgsConstructor
public class ChatMembers {

    @EmbeddedId
    private ChatMembersId id;
}