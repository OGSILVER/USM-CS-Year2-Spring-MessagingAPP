package dev.skirtty.webmessaging.services;

import dev.skirtty.webmessaging.models.AppConfig;
import dev.skirtty.webmessaging.repositories.AppConfigRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppConfigService {

    private final AppConfigRepository appConfigRepository;

    public AppConfig getAppDetailsById(Long id) {
        return appConfigRepository.findById(id).orElseThrow(() -> new RuntimeException("Nu exista asa id!"));
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

    public AppConfig addAppConfig (String appName, String version, String description) {

        AppConfig appConfig = new AppConfig();

        if (appName != null) {
            appConfig.setApp_name(appName);
        } else {
            throw new RuntimeException("Nu ai pus app name!");
        }

        if (version != null) {
            appConfig.setVersion(version);
        } else {
            throw new RuntimeException("Nu ai pus versiunea!");
        }

        if (description != null) {
            appConfig.setDescription(description);
        } else {
            throw new RuntimeException("Nu ai pus descrierea!");
        }

        appConfigRepository.save(appConfig);

        return appConfigRepository.findById(appConfig.getId()).orElseThrow(() -> new RuntimeException("Nu exista asa id!"));
    }

}