package dev.skirtty.webmessaging.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chats_members")
@Getter
@Setter
@NoArgsConstructor
public class ChatsMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chats chat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}