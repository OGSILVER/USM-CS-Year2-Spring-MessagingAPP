package dev.skirtty.webmessaging.controllers;


import dev.skirtty.webmessaging.dto.RegisterRequest;
import dev.skirtty.webmessaging.dto.UserResponse;
import dev.skirtty.webmessaging.models.Users;
import dev.skirtty.webmessaging.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register-user")
    public ResponseEntity<Long> register(@Valid @RequestBody RegisterRequest request) {
        Long registeredUser = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }


    // this is wrong @reminder to make a request dto for updating user fields and add logic for password modify
    // uncomplete, just for showcase
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody Users user, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
