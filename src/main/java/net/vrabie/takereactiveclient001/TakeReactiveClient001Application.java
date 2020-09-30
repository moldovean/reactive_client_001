package net.vrabie.takereactiveclient001;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Log4j2
@EnableConfigurationProperties
public class TakeReactiveClient001Application {

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        WebClient client = builder
                .baseUrl("http://localhost:8080")
                .build();
        return client;
    }




    public static void main(String[] args) {
        SpringApplication.run(TakeReactiveClient001Application.class, args);
    }

}
