package dev.skirtty.webmessaging.repositories;

import dev.skirtty.webmessaging.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
