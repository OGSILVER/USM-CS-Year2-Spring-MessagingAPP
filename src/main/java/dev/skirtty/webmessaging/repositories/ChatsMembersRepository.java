package dev.skirtty.webmessaging.repositories;

import dev.skirtty.webmessaging.models.ChatsMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatsMembersRepository extends JpaRepository<ChatsMembers, Long> {

    List<ChatsMembers> findByChat_Id(Long chatId);

    boolean existsByChat_IdAndUser_Id(Long chatId, Long userId);

    @Modifying
    @Transactional
    void deleteByChat_IdAndUser_Id(Long chatId, Long userId);

    @Query("""
        SELECT CASE WHEN COUNT(cm) > 0 THEN true ELSE false END
        FROM ChatsMembers cm
        WHERE cm.chat.id IN (
            SELECT cm1.chat.id FROM ChatsMembers cm1 WHERE cm1.user.id = :firstUserId
        )
        AND cm.user.id = :secondUserId
    """)
    boolean existsByUsersId(@Param("firstUserId") Long firstUserId,
                            @Param("secondUserId") Long secondUserId);

    @Query("""
        SELECT cm.chat.id
        FROM ChatsMembers cm
        WHERE cm.chat.id IN (
            SELECT cm1.chat.id FROM ChatsMembers cm1 WHERE cm1.user.id = :firstUserId
        )
        AND cm.user.id = :secondUserId
    """)
    Optional<Long> findChatIdByUsersId(@Param("firstUserId") Long firstUserId,
                                       @Param("secondUserId") Long secondUserId);
}