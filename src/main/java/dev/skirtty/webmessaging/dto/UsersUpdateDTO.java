package dev.skirtty.webmessaging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersUpdateDTO {
    private Long id;
    private String username;
    private String email;

    public UsersUpdateDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
