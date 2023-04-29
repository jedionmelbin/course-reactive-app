package com.xprotec.app.creation;

import com.xprotec.app.model.Person;
import io.reactivex.rxjava3.core.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Creation {
    private static final Logger log = LoggerFactory.getLogger(Creation.class);

    public void justFrom(){
        Mono.just(new Person(1,"Jedion", "Paucar", 30));
    }

    public void empty(){
        Mono.empty();
        Flux.empty();
        Observable.empty();
    }

    public void range(){
        Flux.range(0,3)
                .doOnNext(c-> log.info("info " + c))
                .subscribe();
    }

    public void repeat(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",30));
        personList.add(new Person(2,"Maria","Lucia",60));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .repeat(3)
                .subscribe(c-> log.info(c.toString()));

        Mono.just(new Person(1,"Jedion","Paucar",30))
                .repeat(3)
                .subscribe(c->log.info(c.toString()));
    }
}
