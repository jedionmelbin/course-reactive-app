package com.xprotec.reactive.filters;

import com.xprotec.reactive.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Filters {
    private static  final Logger log = LoggerFactory.getLogger(Filters.class);

    public void filters(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(2,"Maria","Lucia",60));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .filter(p->p.getAge() > 28)
                .subscribe(c-> log.info(c.toString()));
    }

    public void distinct(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .distinct()
                .subscribe(c->log.info(c.toString()));
    }

    public void takes(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .take(2)
                .subscribe(c->log.info(c.toString()));
    }

    public void skip(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .skip(2)
                .subscribe(c->log.info(c.toString()));
    }

    public void skipLast(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(1,"Jedion","Paucar",10));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList)
                .skipLast(2)
                .subscribe(c->log.info(c.toString()));
    }
}
