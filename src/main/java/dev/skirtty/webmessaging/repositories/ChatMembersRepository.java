package dev.skirtty.webmessaging.repositories;
import dev.skirtty.webmessaging.models.ChatMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMembersRepository extends JpaRepository<ChatMembers, Long> {
}
