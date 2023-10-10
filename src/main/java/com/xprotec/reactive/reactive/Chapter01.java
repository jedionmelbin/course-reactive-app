package com.xprotec.reactive.reactive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Chapter01 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //Flux and subscribe, error, onComplete, operator doOnNext
    public void program1() {
        Flux<String> names = Flux
                .just("Angelica", "Andrea", "Lina", "xs", "Juan").
                doOnNext(e -> {
                    if (e.isEmpty()) {
                        throw new RuntimeException("The value is empty");
                    } else {
                        System.out.println(e);
                    }
                });

        //Concurrency and multitarea
        names.subscribe(logger::info,
                error -> logger.error(error.getMessage()),
                new Runnable() {
                    @Override
                    public void run() {
                        logger.info("The operation is completed");
                    }
                });
    }
}
