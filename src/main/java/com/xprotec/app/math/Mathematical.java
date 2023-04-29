package com.xprotec.app.math;

import com.xprotec.app.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Mathematical {
    private static  final Logger log = LoggerFactory.getLogger(Mathematical.class);

    public void average(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",30));
        personList.add(new Person(2,"Maria","Lucia",60));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .collect(Collectors.averagingInt(c->c.getAge()))
                .subscribe(c-> log.info(c.toString()));
    }

    public void count(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",30));
        personList.add(new Person(2,"Maria","Lucia",60));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .count()
                .subscribe(c-> log.info("Cantidad" + c.toString()));
    }

    public void min(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",30));
        personList.add(new Person(2,"Maria","Lucia",60));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .collect(Collectors.minBy(Comparator.comparing(Person::getAge)))
                .subscribe(c-> log.info("Cantidad" + c.toString()));
    }

    public void sum(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",30));
        personList.add(new Person(2,"Maria","Lucia",60));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .collect(Collectors.summingInt(Person::getAge))
                .subscribe(c-> log.info("Cantidad" + c.toString()));
    }
}
