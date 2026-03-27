package dev.skirtty.webmessaging.dto;

import lombok.Data;

@Data
public class AppConfigDTO {
    private String app_name;
    private String version;
    private String description;
}
