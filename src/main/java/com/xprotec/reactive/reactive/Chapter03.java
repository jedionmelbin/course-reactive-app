package com.xprotec.reactive.reactive;

import com.xprotec.reactive.model.Customer;
import reactor.core.publisher.Flux;

public class Chapter03 {

    //Observable its inmutable
    public void program1() {
        Flux<String> names = Flux
                .just("Angelica", "Andrea", "Lina", "Lucia", "Juan", "Ingrid")
                .filter(c -> c.equals("Lina"))
                .doOnNext(c -> {
                    if (c.isEmpty()) {
                        throw new RuntimeException("Its string is empty");
                    }
                }).map(c -> {
                    String fullName = c;
                    return "Alis" + fullName;
                });

        names.subscribe(c -> {
            System.out.println(c);
        }, error -> {
            System.out.println("Error");
        }, new Runnable() {
            @Override
            public void run() {
                System.out.println("Finish");
            }
        });
    }
}
