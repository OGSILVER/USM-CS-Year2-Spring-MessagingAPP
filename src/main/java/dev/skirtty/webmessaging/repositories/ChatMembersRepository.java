package dev.skirtty.webmessaging.repositories;

import dev.skirtty.webmessaging.HelperClasses.ChatMembersId;
import dev.skirtty.webmessaging.models.ChatMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChatMembersRepository extends JpaRepository<ChatMembers, ChatMembersId> {

    @Query("SELECT cm FROM ChatMembers cm WHERE cm.id.chat.id = :chatId")
    List<ChatMembers> findByChatId(@Param("chatId") Long chatId);

    @Query("SELECT COUNT(cm) > 0 FROM ChatMembers cm WHERE cm.id.chat.id = :chatId AND cm.id.user.id = :userId")
    boolean existsByChatIdAndUserId(@Param("chatId") Long chatId, @Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ChatMembers cm WHERE cm.id.chat.id = :chatId AND cm.id.user.id = :userId")
    void deleteByChatIdAndUserId(@Param("chatId") Long chatId, @Param("userId") Long userId);
}