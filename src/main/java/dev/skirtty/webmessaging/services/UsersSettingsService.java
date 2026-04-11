package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.UsersSettingsDTO;
import dev.skirtty.webmessaging.exceptions.ResourceIsNullException;
import dev.skirtty.webmessaging.exceptions.ResourceExistsException;
import dev.skirtty.webmessaging.exceptions.ResourceNotFoundException;
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
        if (userId == null) {
            throw  new ResourceIsNullException("Parametrul userID este gol");
        }

        return userSettingsRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Nu au fost gasite setarile pentru acest utilizator: " + userId));
    }

    public UsersSettings patch(Long userId, UsersSettingsDTO updates) {
        if (userId == null) {
            throw  new ResourceIsNullException("Parametrul userID este gol");
        }
        UsersSettings settings = getByUserId(userId);
        settings.setTheme(updates.getTheme());
        settings.setAccent_color(updates.getAccent_color());
        settings.setLanguage(updates.getLanguage());
        settings.setNotifications_enabled(updates.getNotifications_enabled());

        return userSettingsRepository.save(settings);
    }

    public UsersSettings createDefaults(Long userId) {
        if (userId == null) {
            throw  new ResourceIsNullException("Parametrul userID este gol");
        }
        if (userSettingsRepository.findByUserId(userId).isPresent()) {
            throw new ResourceExistsException("Setarile pentru acest utilizator deja exista: " + userId);
        }
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilizatorul nu a fost gasit: " + userId));

        UsersSettings settings = new UsersSettings();
        settings.setUser(user);
        settings.setTheme("light");
        settings.setAccent_color("blue");
        settings.setLanguage("en");
        settings.setNotifications_enabled(true);
        return userSettingsRepository.save(settings);
    }

}
