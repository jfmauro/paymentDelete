package be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driving.controler;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class InfoController {

    // Endpoint qui retourne un objet JSON
    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "API REST Spring Boot");
        response.put("version", "1.0.2");
        response.put("timestamp", LocalDateTime.now());
        response.put("status", "Opérationnel");
        return response;
    }
}