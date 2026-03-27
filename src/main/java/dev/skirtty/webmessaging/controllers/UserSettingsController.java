package dev.skirtty.webmessaging.controllers;


import dev.skirtty.webmessaging.dto.UserSettingsDTO;
import dev.skirtty.webmessaging.models.UserSettings;
import dev.skirtty.webmessaging.services.UserSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user-settings")
@RequiredArgsConstructor
public class UserSettingsController {

    private final UserSettingsService userSettingsService;
    private final UserController userController;

    @GetMapping("/{userId}")
    public ResponseEntity<UserSettings> getUserSettings(@PathVariable Long userId) {
        return ResponseEntity.ok(userSettingsService.getByUserId(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserSettings> patchSettings(@PathVariable Long userId, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(userSettingsService.patch(userId, updates));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserSettings> createSettings(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userSettingsService.createDefaults(userId));
    }
}
