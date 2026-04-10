package dev.skirtty.webmessaging.controllers;


import dev.skirtty.webmessaging.dto.RegisterRequest;
import dev.skirtty.webmessaging.dto.UsersDTO;
import dev.skirtty.webmessaging.dto.UsersUpdateDTO;
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
    public ResponseEntity<UsersDTO> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(request));
    }

    // this is wrong @reminder to make a request dto for updating user fields and add logic for password modify
    // uncomplete, just for showcase
    @PatchMapping("/update-username/{id}")
    public ResponseEntity<UsersDTO> update(@Valid @RequestBody UsersUpdateDTO user, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUsername(id, user.getUsername()));
    }

    @PatchMapping("/update-email/{id}")

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }


}
