package dev.skirtty.webmessaging.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // In a real app, 'sender' would be a relationship to your User entity
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User sender;

    // Default constructor for JPA
    public Message() {
        this.timestamp = LocalDateTime.now();
    }

    public Message(String content, User sender) {
        this.content = content;
        this.sender = sender;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters...
    public Long getId() { return id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }
}