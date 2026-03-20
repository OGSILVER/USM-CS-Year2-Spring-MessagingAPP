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
        return userSettingsRepository.findById(userId).orElseThrow(() -> new RuntimeException("User settings not found for user id: " + userId));
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
        updates.forEach((key, value) -> {
            switch (key) {
                case "theme" -> settings.setTheme((String) value);
                case "accent_color" -> settings.setAccent_color((String) value);
                case "language" -> settings.setLanguage((String) value);
                case "notifications_enabled" -> settings.setNotifications_enabled((Boolean) value);
            }
        });
        return userSettingsRepository.save(settings);
    }

    public UserSettings createDefaults(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        UserSettings settings = new UserSettings();
        settings.setUser_id(user);
        settings.setTheme("light");
        settings.setAccent_color("blue");
        settings.setLanguage("en");
        settings.setNotifications_enabled(true);
        return userSettingsRepository.save(settings);
    }

}
