package dev.skirtty.webmessaging.dto;

import dev.skirtty.webmessaging.models.Users;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class UsersDTO {
    @NotNull(message = "Nu poti pune id ul null!")
    private Long id;

    @NotNull(message = "Nu poti pune numele null!")
    private String username;

    @NotNull(message = "Nu poti pune email ul null!")
    private String email;

    private boolean isOnline;
    private LocalDateTime last_seen;
    private LocalDateTime created_at;

    public UsersDTO(Users user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.isOnline = user.isOnline();
        this.last_seen = user.getLast_seen();
        this.created_at = user.getCreated_at();
    }


}