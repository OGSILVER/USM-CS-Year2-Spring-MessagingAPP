package dev.skirtty.webmessaging.services;

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
        return userSettingsRepository.findByUserId(userId)  // ✅ direct Long, fără findById
                .orElseThrow(() -> new RuntimeException("User settings not found"));
    }

    public UsersSettings patch(Long userId, Map<String, Object> updates) {
        UsersSettings settings = getByUserId(userId);
        if (updates.containsKey("theme") && updates.get("theme") != null) {
            settings.setTheme((String) updates.get("theme"));
        }
        if (updates.containsKey("accent_color") && updates.get("accent_color") != null) {
            settings.setTheme((String) updates.get("accent_color"));
        }
        if (updates.containsKey("language") && updates.get("language") != null) {
            settings.setTheme((String) updates.get("language"));
        }
        if (updates.containsKey("notifications_enabled") && updates.get("notifications_enabled") != null) {
            settings.setTheme((String) updates.get("notifications_enabled"));
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
