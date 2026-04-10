package dev.skirtty.webmessaging.controllers;

import dev.skirtty.webmessaging.dto.AppConfigDTO;
import dev.skirtty.webmessaging.models.AppConfig;
import dev.skirtty.webmessaging.services.AppConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/app_config")
@RequiredArgsConstructor
public class AppConfigController {

    final private AppConfigService appConfigService;

    @PatchMapping("/update/{id}/")
    public AppConfig updateConfig (@PathVariable Long id, @RequestParam String appName, @RequestParam String version, @RequestParam String description) {
        return appConfigService.updateAppConfigById(id, appName, version, description);
    }

    @PostMapping("/create/")
    public ResponseEntity<AppConfigDTO> createConfig (@Valid @RequestBody AppConfigDTO appConfigDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appConfigService.addAppConfig(appConfigDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppConfigDTO> getConfig (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(appConfigService.getAppDetailsById(id));
    }
}
