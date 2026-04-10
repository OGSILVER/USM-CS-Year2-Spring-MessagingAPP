package dev.skirtty.webmessaging.dto;

import dev.skirtty.webmessaging.models.Users;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@NoArgsConstructor
public class UsersDTO {
    private Long id;
    private String username;
    private String email;
    private boolean isOnline;
    private LocalDateTime last_seen;
    private LocalDateTime created_at;


    public UsersDTO(Users user){
        Long id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        boolean isOnline = user.isOnline();
        LocalDateTime last_seen = user.getLast_seen();
        LocalDateTime created_at = user.getCreated_at();
    }


}