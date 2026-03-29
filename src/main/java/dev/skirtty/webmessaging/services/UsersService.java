package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.RegisterRequest;
import dev.skirtty.webmessaging.dto.UsersResponse;
import dev.skirtty.webmessaging.models.Users;
import dev.skirtty.webmessaging.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long registerUser(RegisterRequest request) {
        Users newUser = new Users();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(newUser).getUser_id();
    }

    public UsersResponse updateUser(Long id, Users user) {
        return null;
    }

    public UsersResponse getUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setId(user.getUser_id());
        usersResponse.setUsername(user.getUsername());
        usersResponse.setEmail(user.getEmail());
        usersResponse.setOnline(user.isOnline());
        usersResponse.setLast_seen(user.getLast_seen() != null);
        usersResponse.setCreated_at(user.getCreated_at());

        return usersResponse;
    }
}
