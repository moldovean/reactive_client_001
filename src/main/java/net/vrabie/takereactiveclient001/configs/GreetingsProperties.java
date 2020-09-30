package net.vrabie.takereactiveclient001.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "greet")
@Data
public class GreetingsProperties {
    private String greeting;
    private Map<String, String> greetings;
}
