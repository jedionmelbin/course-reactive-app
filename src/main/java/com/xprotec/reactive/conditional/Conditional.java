package com.xprotec.reactive.conditional;

import com.xprotec.reactive.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Conditional {
    private static final Logger log = LoggerFactory.getLogger(Conditional.class);

    public void defaultEmpty() {
        Mono.empty().defaultIfEmpty(new Person(1, "Person 1", "Person 3", 90)).subscribe(x -> log.info(x.toString()));

        Flux.empty().defaultIfEmpty(new Person(1, "Person 1", "Person 3", 90)).subscribe(x -> log.info(x.toString()));
    }

    public void takeUntil() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Jedion", "Paucar", 30));
        personList.add(new Person(2, "Maria", "Lucia", 60));
        personList.add(new Person(3, "Jedion", "Paucar", 40));

        // Solo obtiene hasta donde encuentra la coleccion.
        Flux.fromIterable(personList)
                .takeUntil(p -> p.getAge() > 20)
                .subscribe(c-> log.info(c.toString()));
    }

    public void timeoutCond(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Jedion", "Paucar", 30));
        personList.add(new Person(2, "Maria", "Lucia", 60));
        personList.add(new Person(3, "Jedion", "Paucar", 40));

        Flux.fromIterable(personList)
                .delayElements(Duration.ofSeconds(1))
                .timeout(Duration.ofSeconds(2))
                .subscribe(x->log.info(x.toString()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
