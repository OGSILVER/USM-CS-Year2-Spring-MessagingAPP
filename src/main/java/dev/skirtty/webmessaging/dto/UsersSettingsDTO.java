package dev.skirtty.webmessaging.dto;

import lombok.Data;

@Data
public class UsersSettingsDTO {
    private String theme;
    private String accent_color;
    private String language;
    private boolean notifications_enabled;
}
