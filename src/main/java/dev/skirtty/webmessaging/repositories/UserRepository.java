package dev.skirtty.webmessaging.repositories;

import dev.skirtty.webmessaging.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}
