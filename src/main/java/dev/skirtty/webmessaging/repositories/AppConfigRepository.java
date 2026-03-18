package dev.skirtty.webmessaging.repositories;

import dev.skirtty.webmessaging.models.AppConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppConfigRepository extends JpaRepository<AppConfig, Long> {
}
