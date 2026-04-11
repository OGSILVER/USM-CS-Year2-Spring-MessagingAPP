package dev.skirtty.webmessaging.controllers;


import dev.skirtty.webmessaging.dto.UsersSettingsDTO;
import dev.skirtty.webmessaging.models.UsersSettings;
import dev.skirtty.webmessaging.services.UsersSettingsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user_settings")
@RequiredArgsConstructor
public class UsersSettingsController {

    private final UsersSettingsService usersSettingsService;
    private final UsersController userController;

    @GetMapping("/{userId}")
    public ResponseEntity<UsersSettings> getUserSettings(@PathVariable Long userId) {
        return ResponseEntity.ok(usersSettingsService.getByUserId(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UsersSettings> patchSettings(@Valid @PathVariable Long userId, @RequestBody UsersSettingsDTO updates) {
        return ResponseEntity.ok(usersSettingsService.patch(userId, updates));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UsersSettings> createSettings(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersSettingsService.createDefaults(userId));
    }
}
