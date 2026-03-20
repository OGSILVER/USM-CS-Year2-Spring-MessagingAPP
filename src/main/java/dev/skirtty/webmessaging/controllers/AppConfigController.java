package dev.skirtty.webmessaging.controllers;

import dev.skirtty.webmessaging.models.AppConfig;
import dev.skirtty.webmessaging.services.AppConfigService;
import lombok.RequiredArgsConstructor;
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
    public AppConfig createConfig (@RequestParam String appName, @RequestParam String version, @RequestParam String description) {
        return appConfigService.addAppConfig(appName, version,description);
    }

    @GetMapping("/get/{id}/")
    public AppConfig getConfig (@PathVariable Long id) {
        return appConfigService.getAppDetailsById(id);
    }
}
