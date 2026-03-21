package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.UserSettingsDTO;
import dev.skirtty.webmessaging.models.UserSettings;
import dev.skirtty.webmessaging.models.Users;
import dev.skirtty.webmessaging.repositories.UserRepository;
import dev.skirtty.webmessaging.repositories.UserSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserSettingsService {

    private final UserRepository userRepository;
    private final UserSettingsRepository userSettingsRepository;

    public UserSettings getByUserId(Long userId) {
        return userSettingsRepository.findByUserId(userId)  // ✅ direct Long, fără findById
                .orElseThrow(() -> new RuntimeException("User settings not found"));
    }

    public UserSettings update(Long userId, UserSettingsDTO userSettings) {
        UserSettings settings = getByUserId(userId);
        settings.setTheme(userSettings.getTheme());
        settings.setAccent_color(userSettings.getAccent_color());
        settings.setLanguage(userSettings.getLanguage());
        settings.setNotifications_enabled(userSettings.isNotifications_enabled());
        return userSettingsRepository.save(settings);
    }

    public UserSettings patch(Long userId, Map<String, Object> updates) {
        UserSettings settings = getByUserId(userId);
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

    public UserSettings createDefaults(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        UserSettings settings = new UserSettings();
        settings.setUser(user);
        settings.setTheme("light");
        settings.setAccent_color("blue");
        settings.setLanguage("en");
        settings.setNotifications_enabled(true);
        return userSettingsRepository.save(settings);
    }

}
