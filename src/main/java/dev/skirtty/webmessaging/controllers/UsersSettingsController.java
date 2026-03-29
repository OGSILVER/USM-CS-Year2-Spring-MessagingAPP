package dev.skirtty.webmessaging.controllers;


import dev.skirtty.webmessaging.models.UsersSettings;
import dev.skirtty.webmessaging.services.UsersSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user-settings")
@RequiredArgsConstructor
public class UsersSettingsController {

    private final UsersSettingsService usersSettingsService;
    private final UsersController userController;

    @GetMapping("/{userId}")
    public ResponseEntity<UsersSettings> getUserSettings(@PathVariable Long userId) {
        return ResponseEntity.ok(usersSettingsService.getByUserId(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UsersSettings> patchSettings(@PathVariable Long userId, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(usersSettingsService.patch(userId, updates));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UsersSettings> createSettings(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersSettingsService.createDefaults(userId));
    }
}
