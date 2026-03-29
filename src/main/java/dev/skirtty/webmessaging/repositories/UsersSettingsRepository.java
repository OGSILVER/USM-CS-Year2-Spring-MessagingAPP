package dev.skirtty.webmessaging.repositories;

import dev.skirtty.webmessaging.models.UsersSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersSettingsRepository extends JpaRepository<UsersSettings, Long> {
    @Query("SELECT s FROM UsersSettings s WHERE s.user.id = :userId")
    Optional<UsersSettings> findByUserId(@Param("userId") Long userId);
}
