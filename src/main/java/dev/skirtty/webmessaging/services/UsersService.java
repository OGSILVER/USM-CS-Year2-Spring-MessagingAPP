package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.RegisterRequest;
import dev.skirtty.webmessaging.dto.UsersDTO;
import dev.skirtty.webmessaging.dto.UsersUpdateDTO;
import dev.skirtty.webmessaging.models.Users;
import dev.skirtty.webmessaging.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersDTO registerUser(RegisterRequest request) {
        Users newUser = new Users();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        Users changedUser = userRepository.findById(userRepository.save(newUser).getId()).orElseThrow(() -> new RuntimeException(("User not found")));

        return(new UsersDTO(newUser));

    }

    public UsersDTO updateUsername(Long id, String username) {
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Uer not found"));
        user.setUsername(username);
        return new UsersDTO(user);

    }

    public UsersDTO updateEmail(Long id, UsersUpdateDTO newUser){
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(newUser.getEmail());
        return new UsersDTO(user);
    }

    public UsersDTO getUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UsersDTO(user);
    }

}
