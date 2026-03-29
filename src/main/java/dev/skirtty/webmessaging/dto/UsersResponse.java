package dev.skirtty.webmessaging.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UsersResponse {
    private long id;
    private String username;
    private String email;
    private boolean isOnline;
    private boolean last_seen;
    private LocalDateTime created_at;
}
