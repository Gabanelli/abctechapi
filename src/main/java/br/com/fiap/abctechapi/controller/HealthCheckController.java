package br.com.fiap.abctechapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RestController
@RequestMapping("/")
public class HealthCheckController {

    private static final String PROPERTIES_FILE = "application.yaml";

    @GetMapping("version")
    public ResponseEntity<Object> version() throws IOException {

        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        properties.load(inputStream);

        return ResponseEntity.ok(properties.getProperty("build.name") + " - " + properties.getProperty("build.version"));
    }

}
