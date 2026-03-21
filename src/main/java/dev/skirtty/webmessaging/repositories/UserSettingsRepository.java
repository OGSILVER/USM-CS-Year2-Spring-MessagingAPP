package dev.skirtty.webmessaging.repositories;

import dev.skirtty.webmessaging.models.UserSettings;
import dev.skirtty.webmessaging.models.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
    @Query("SELECT s FROM UserSettings s WHERE s.user.user_id = :userId")
    Optional<UserSettings> findByUserId(@Param("userId") Long userId);
}
