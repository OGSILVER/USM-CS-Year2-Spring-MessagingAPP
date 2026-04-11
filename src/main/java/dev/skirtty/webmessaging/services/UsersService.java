package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.RegisterRequest;
import dev.skirtty.webmessaging.dto.UsersDTO;
import dev.skirtty.webmessaging.dto.UsersUpdateDTO;
import dev.skirtty.webmessaging.exceptions.ResourceExistsException;
import dev.skirtty.webmessaging.exceptions.ResourceNotFoundException;
import dev.skirtty.webmessaging.models.Users;
import dev.skirtty.webmessaging.repositories.UsersRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsersSettingsService usersSettingsService;

    public UsersDTO registerUser(RegisterRequest request) {
        Users newUser = new Users();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        boolean boolEmail = existsByEmail(request.getEmail());
        boolean boolUsername = existsByUsername(request.getUsername());

        if(boolEmail){
            throw new ResourceExistsException("A user with this email already exists");
        }else if (boolUsername){
            throw new ResourceExistsException("A user with this username already exists");
        }else{
            userRepository.save(newUser);
            usersSettingsService.createDefaults(newUser.getId());
            return(new UsersDTO(newUser));
        }



    }

    public UsersDTO updateUsername(Long id, String username) {
        Users user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean bool = existsByUsername(username);
        if(bool){
            throw new ResourceExistsException("A user with this name already exists!");
        }
        user.setUsername(username);
        userRepository.save(user);
        return new UsersDTO(user);

    }

    public UsersDTO updateEmail(Long id, String email){
        Users user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean bool = existsByEmail(email);

        if(bool){
            throw new ResourceExistsException("A user with this email already exists!");
        }

        user.setEmail(email);
        return new UsersDTO(user);
    }

    public UsersDTO getUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new UsersDTO(user);
    }

    public UsersDTO getUserByEmail(String email){
        Users user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("A user with this email does not exist"));
        return new UsersDTO(user);
    }

    public UsersDTO getUserByUsername(String username){
        Users user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("A user with this username does not exist"));
        return new UsersDTO(user);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
}