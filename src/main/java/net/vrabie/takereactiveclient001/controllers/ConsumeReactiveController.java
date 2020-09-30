package net.vrabie.takereactiveclient001.controllers;

import lombok.RequiredArgsConstructor;
import net.vrabie.takereactiveclient001.daos.GreetingsResponse;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RestController
@RequiredArgsConstructor
public class ConsumeReactiveController {
    private final WebClient webClient;
    private final ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

    @GetMapping(value = "consume/{name}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<GreetingsResponse> consumeFluxFromWeb(@PathVariable String name) {
        ReactiveCircuitBreaker consume = reactiveCircuitBreakerFactory.create("consume");
        Flux<GreetingsResponse> greetingsResponseFlux = webClient.get()
                .uri("/greetings/{pathVar}", name)
                .retrieve()
                .bodyToFlux(GreetingsResponse.class);
        Function<Throwable, Flux<GreetingsResponse>> server_temporarily_unavailable = throwable -> Flux.just(new GreetingsResponse("Server temporarily Unavailable"));
        return consume.run(greetingsResponseFlux, server_temporarily_unavailable);
//        return greetingsResponseFlux;

    }

}
