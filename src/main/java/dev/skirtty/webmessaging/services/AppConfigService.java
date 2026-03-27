package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.dto.AppConfigDTO;
import dev.skirtty.webmessaging.models.AppConfig;
import dev.skirtty.webmessaging.repositories.AppConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppConfigService {

    private final AppConfigRepository appConfigRepository;

    public AppConfigDTO getAppDetailsById(Long id) {
        AppConfig appConfig = appConfigRepository.findById(id).orElseThrow(() -> new RuntimeException("Nu exista asa id!"));
        AppConfigDTO appConfigRequestDTO = new AppConfigDTO();

        appConfigRequestDTO.setApp_name(appConfig.getApp_name());
        appConfigRequestDTO.setDescription(appConfig.getDescription());
        appConfigRequestDTO.setVersion(appConfig.getVersion());

        return appConfigRequestDTO;
    }

    public AppConfig updateAppConfigById(Long id, String appName, String version, String description) {
        AppConfig appConfig = appConfigRepository.findById(id).orElseThrow(() -> new RuntimeException("Nu exista asa id!"));

        if (appName != null) {
            appConfig.setApp_name(appName);
        }
        if (version != null) {
            appConfig.setVersion(version);
        }
        if (description != null) {
            appConfig.setDescription(description);
        }

        return appConfigRepository.save(appConfig);
    }

    public AppConfigDTO addAppConfig (AppConfigDTO appConfigDTO) {

        AppConfig appConfig = new AppConfig();

        if (appConfigDTO.getApp_name() != null) {
            appConfig.setApp_name(appConfigDTO.getApp_name());
        } else {
            throw new RuntimeException("Nu ai pus app name!");
        }

        if (appConfigDTO.getVersion() != null) {
            appConfig.setVersion(appConfigDTO.getVersion());
        } else {
            throw new RuntimeException("Nu ai pus versiunea!");
        }

        if (appConfigDTO.getDescription() != null) {
            appConfig.setDescription(appConfigDTO.getDescription());
        } else {
            throw new RuntimeException("Nu ai pus descrierea!");
        }

        appConfigRepository.save(appConfig);

        return appConfigDTO;
    }

}