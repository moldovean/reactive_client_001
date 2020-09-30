package net.vrabie.takereactiveclient001.controllers;

import lombok.RequiredArgsConstructor;
import net.vrabie.takereactiveclient001.configs.GreetingsProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class GreetingReactiveController {
    private final GreetingsProperties greetingsProperties;

    @GetMapping("/hi")
    public Flux<String> hello() {
        return Flux.just(greetingsProperties.getGreeting());
    }

    @GetMapping("/hi/{language}")
    public Flux<String> hello(@PathVariable String language) {
        return Flux.just(greetingsProperties.getGreetings().get(language));
    }

}
