package dev.skirtty.webmessaging.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsersSettingsDTO {

    @NotBlank(message = "Nu poti sa trimiti ceva gol")
    private String theme;

    @NotBlank(message = "Nu poti sa trimiti ceva gol")
    private String accent_color;

    @NotBlank(message = "Nu poti sa trimiti ceva gol")
    private String language;

    private Boolean notifications_enabled;
}
