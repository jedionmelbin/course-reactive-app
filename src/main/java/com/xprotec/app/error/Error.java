package com.xprotec.app.error;

import com.xprotec.app.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Error {
    private static final Logger log = LoggerFactory.getLogger(Error.class);

    public void  retry(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .concatWith(Flux.error(new RuntimeException("ERROR OCURRED")))
                .retry(1)
                .doOnNext(x-> log.error(x.toString()))
                .subscribe();

    }

    public void returnError(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .concatWith(Flux.error(new RuntimeException("ERROR OCURRED")))
                .onErrorReturn(new Person(2,"Maria","Espinoza",30))
                .subscribe(x-> log.error(x.toString()));

    }

    public void errorResumme(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .concatWith(Flux.error(new RuntimeException("OCCURRED ERROR")))
                .onErrorResume(e-> Mono.just(new Person(2,"Jedion","Paucar", 20)))
                .subscribe();
    }

    public void errorMap(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .concatWith(Flux.error(new RuntimeException("OCCURRED ERROR")))
                .onErrorMap(e-> new InterruptedException(e.getMessage()))
                .subscribe(c->log.info(c.toString()));
    }
}
