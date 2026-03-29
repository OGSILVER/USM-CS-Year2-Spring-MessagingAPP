package dev.skirtty.webmessaging.controllers;


import dev.skirtty.webmessaging.dto.RegisterRequest;
import dev.skirtty.webmessaging.dto.UsersResponse;
import dev.skirtty.webmessaging.models.Users;
import dev.skirtty.webmessaging.services.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService userService;

    @PostMapping("/register-user")
    public ResponseEntity<Long> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(request));
    }

    // this is wrong @reminder to make a request dto for updating user fields and add logic for password modify
    // uncomplete, just for showcase
    @PutMapping("/{id}")
    public ResponseEntity<UsersResponse> update(@Valid @RequestBody Users user, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

}
