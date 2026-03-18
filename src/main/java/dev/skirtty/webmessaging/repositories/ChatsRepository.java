package dev.skirtty.webmessaging.repositories;
import dev.skirtty.webmessaging.models.Chats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatsRepository extends JpaRepository<Chats, Long> {
}
