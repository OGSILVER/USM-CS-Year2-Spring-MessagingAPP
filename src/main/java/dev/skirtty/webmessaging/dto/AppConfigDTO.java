package dev.skirtty.webmessaging.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AppConfigDTO {

    @NotBlank(message = "Nu trebuie sa fie gol")
    @Length(max = 20, message = "Nu trebuie sa depaseasca 20 de caractere!")
    private String app_name;

    @NotBlank(message = "Nu trebuie sa fie gol")
    @Length(max = 20, message = "Nu trebuie sa depaseasca 20 de caractere!")
    private String version;

    @NotBlank(message = "Nu trebuie sa fie gol")
    @Length(max = 100, message = "Nu trebuie sa depaseasca 100 de caractere!")
    private String description;
}
