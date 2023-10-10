package com.xprotec.reactive.reactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Chapter04 {
    public void program1() {
        ///Convert una list a reactive
        List<String> users = new ArrayList<>();
        users.add("Maria");
        users.add("Pedro");
        users.add("Juan");
        users.add("Bruce");
        users.add("Antuanne");

        Flux<String> userFlux = Flux.fromIterable(users);
    }

    public void program2() {
        ///Convert una list a reactive
        String users = "Reactive";
        Mono<String> userMono = Mono.just(users);
    }
}
