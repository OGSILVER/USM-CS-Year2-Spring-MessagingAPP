package dev.skirtty.webmessaging.repositories;
import dev.skirtty.webmessaging.models.Chats;
import dev.skirtty.webmessaging.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatsRepository extends JpaRepository<Chats, Long> {

    @Query(value = "SELECT * FROM messages WHERE chat_id = :chatId ORDER BY sent_at DESC", nativeQuery = true)
    Page<Message> findMessagesByChatId(@Param("chatId") Long chatId, Pageable pageable);
}
