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

    @PostMapping("/register_user")
    public ResponseEntity<UsersDTO> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(request));
    }


    @PatchMapping("/update_username/{id}")
    public ResponseEntity<UsersDTO> updateName(@Valid @RequestBody UsersUpdateDTO user) {
        return ResponseEntity.ok(userService.updateUsername(user.getId(), user.getUsername()));
    }

    @PatchMapping("/update_email/{id}")
    public ResponseEntity<UsersDTO> updateEmail(@Valid @RequestBody UsersUpdateDTO user) {
        return ResponseEntity.ok(userService.updateEmail(user.getId(),user.getEmail()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }



}
