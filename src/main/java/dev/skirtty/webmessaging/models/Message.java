package dev.skirtty.webmessaging.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter @Setter @NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime sent_at;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chats chat;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Users sender;
}