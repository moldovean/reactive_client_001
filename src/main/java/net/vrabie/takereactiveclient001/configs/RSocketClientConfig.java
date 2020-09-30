package net.vrabie.takereactiveclient001.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
public class RSocketClientConfig {

    @Bean
    RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        return builder.connectTcp("localhost", 7000).block();
    }
}
