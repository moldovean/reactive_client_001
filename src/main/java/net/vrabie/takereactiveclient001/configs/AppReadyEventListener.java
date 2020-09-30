package net.vrabie.takereactiveclient001.configs;

import lombok.extern.log4j.Log4j2;
import net.vrabie.takereactiveclient001.daos.GreetingsResponse;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
@Log4j2
public class AppReadyEventListener {

    @Bean
    ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(WebClient webClient) {
        return new ApplicationListener<ApplicationReadyEvent>() {
            @Override
            public void onApplicationEvent(ApplicationReadyEvent event) {
                webClient.get()
                        .uri("greeting/{name}", "Adrian")
                        .retrieve()
                        .bodyToFlux(GreetingsResponse.class)
                        .retryWhen(Retry.backoff(5, Duration.ofMillis(2000)))
                        .onErrorResume(ex-> Mono.just(new GreetingsResponse("Eeeek")))
                        .subscribe(log::info);
            }
        };
    }
}
