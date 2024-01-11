package com.xprotec.reactive.reactive;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Chapter11 {

    public void programWidthRange() {
        Flux.just(1, 2, 3, 4)
                .map(i -> (i * 2))
                .zipWith(Flux.range(0, 4), (source, dest) -> {
                    return dest + " " + source.toString();
                }).subscribe(log::info);

    }

    public void intervalTimes() {
        Flux<Integer> range = Flux.range(1, 12);

        Flux<Long> delay = Flux.interval(Duration.ofSeconds(1));

        range.zipWith(delay, (ra, del) -> ra)
                .doOnNext(i -> {
                    log.info(i.toString());
                }).blockLast();
        //.subscribe();
    }
}
