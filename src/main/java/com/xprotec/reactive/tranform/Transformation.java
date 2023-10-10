package com.xprotec.reactive.tranform;


import com.xprotec.reactive.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Transformation {
    private static  final Logger log = LoggerFactory.getLogger(Transformation.class);

    public void map(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",30));
        personList.add(new Person(2,"Maria","Lucia",60));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .map(p-> {
                    p.setAge(p.getAge() + 10);
                    return p;
                }).subscribe(c->log.info(c.toString()));

      Flux<Integer>  flux = Flux.range(0,10);
      Flux<Integer> flx =  flux.map(c-> c + 10);
      flx.subscribe(c->log.info(c.toString()));

    }

    public void  flatMap(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",30));
        personList.add(new Person(2,"Maria","Lucia",60));
        personList.add(new Person(3,"Jedion","Paucar",40));

        //Otro flujo de datos
        Flux.fromIterable(personList)
                .flatMap(c->{
                    c.setAge(c.getAge() + 10);
                    return Mono.just(c);
        }).subscribe(c->log.info(c.toString()));
    }

    public void groupBy(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",30));
        personList.add(new Person(1,"Maria","Lucia",60));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .groupBy(Person::getId)
                .flatMap(Flux::collectList)
                .subscribe(c-> log.info(c.toString()));

    }
}
