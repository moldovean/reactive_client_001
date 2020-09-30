package net.vrabie.takereactiveclient001.controllers;

import lombok.RequiredArgsConstructor;
import net.vrabie.takereactiveclient001.daos.GreetingsRequest;
import net.vrabie.takereactiveclient001.daos.GreetingsResponse;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ReactiveControllerRSocket {
    private final RSocketRequester rSocketRequester;

    @GetMapping(value = "rsockets/{name}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<GreetingsResponse> rsocketPoint(@PathVariable String name) {
        Flux<GreetingsResponse> greetingsResponseFlux =
                rSocketRequester
                        .route("rgreetings.{timeInMillis}", 800)
                        .data(new GreetingsRequest(name))
                        .retrieveFlux(GreetingsResponse.class);
        return greetingsResponseFlux;
    }

}
