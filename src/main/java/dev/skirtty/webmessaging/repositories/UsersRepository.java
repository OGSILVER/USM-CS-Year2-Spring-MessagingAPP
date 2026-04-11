package dev.skirtty.webmessaging.repositories;

import dev.skirtty.webmessaging.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
}
