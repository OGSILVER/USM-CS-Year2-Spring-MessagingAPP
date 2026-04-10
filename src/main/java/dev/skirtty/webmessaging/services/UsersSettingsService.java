package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.UsersSettingsDTO;
import dev.skirtty.webmessaging.models.UsersSettings;
import dev.skirtty.webmessaging.models.Users;
import dev.skirtty.webmessaging.repositories.UsersRepository;
import dev.skirtty.webmessaging.repositories.UsersSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsersSettingsService {

    private final UsersRepository userRepository;
    private final UsersSettingsRepository userSettingsRepository;

    public UsersSettings getByUserId(Long userId) {
        return userSettingsRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User settings not found"));
    }

    public UsersSettings patch(Long userId, UsersSettingsDTO updates) {
        UsersSettings settings = getByUserId(userId);
        if (updates.getTheme() != null) {
            settings.setTheme(updates.getTheme());
        }
        if (updates.getAccent_color() != null) {
            settings.setAccent_color(updates.getAccent_color());
        }
        if (updates.getLanguage() != null) {
            settings.setLanguage(updates.getLanguage());
        }
        if (updates.getNotifications_enabled() != null) {
            settings.setNotifications_enabled(updates.getNotifications_enabled());
        }
        return userSettingsRepository.save(settings);
    }

    public UsersSettings createDefaults(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        UsersSettings settings = new UsersSettings();
        settings.setUser(user);
        settings.setTheme("light");
        settings.setAccent_color("blue");
        settings.setLanguage("en");
        settings.setNotifications_enabled(true);
        return userSettingsRepository.save(settings);
    }

}
