package dev.skirtty.webmessaging.repositories;

import dev.skirtty.webmessaging.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
