package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.RegisterRequest;
import dev.skirtty.webmessaging.dto.UserResponse;
import dev.skirtty.webmessaging.models.Users;
import dev.skirtty.webmessaging.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long registerUser(RegisterRequest request) {
        Users newUser = new Users();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(newUser).getUser_id();
    }

    public UserResponse updateUser(Long id, Users user) {
        return null;
    }

    public UserResponse getUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getUser_id());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setOnline(user.isOnline());
        userResponse.setLast_seen(user.getLast_seen() != null);
        userResponse.setCreated_at(user.getCreated_at());

        return userResponse;
    }
}
