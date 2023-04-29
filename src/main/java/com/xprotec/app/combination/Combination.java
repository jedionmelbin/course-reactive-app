package com.xprotec.app.combination;


import com.xprotec.app.model.Person;
import com.xprotec.app.model.Sale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Combination {
    private static final Logger log = LoggerFactory.getLogger(Combination.class);

    public void merge() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Jedion", "Paucar", 30));
        personList.add(new Person(2, "Maria", "Lucia", 60));
        personList.add(new Person(3, "Jedion", "Paucar", 40));

        List<Person> personList2 = new ArrayList<>();
        personList2.add(new Person(1, "Jedion", "Paucar", 30));
        personList2.add(new Person(2, "Maria", "Lucia", 60));
        personList2.add(new Person(3, "Jedion", "Paucar", 40));


        List<Sale> sales =  new ArrayList<>();
        sales.add(new Sale(1, LocalDateTime.now()));

        Flux<Person> flux1 = Flux.fromIterable(personList);
        Flux<Person> flux2 = Flux.fromIterable(personList2);
        Flux<Sale> flux3 = Flux.fromIterable(sales);

        Flux.merge(flux1,flux2, flux3)
                .subscribe(c->log.info(c.toString()));

    }

    public void  zip(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Jedion", "Paucar", 30));
        personList.add(new Person(2, "Maria", "Lucia", 60));
        personList.add(new Person(3, "Jedion", "Paucar", 40));

        List<Person> personList2 = new ArrayList<>();
        personList2.add(new Person(1, "Jedion", "Paucar", 30));
        personList2.add(new Person(2, "Maria", "Lucia", 60));
        personList2.add(new Person(3, "Jedion", "Paucar", 40));


        List<Sale> sales =  new ArrayList<>();
        sales.add(new Sale(1, LocalDateTime.now()));

        Flux<Person> flux1 = Flux.fromIterable(personList);
        Flux<Person> flux2 = Flux.fromIterable(personList2);
        Flux<Sale> flux3 = Flux.fromIterable(sales);

        Flux.zip(flux1,flux2, (p1,p2)-> String.format("%s %s", p1,p2))
                .subscribe(c->log.info(c));
    }

    public void  zipWith(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Jedion", "Paucar", 30));
        personList.add(new Person(2, "Maria", "Lucia", 60));
        personList.add(new Person(3, "Jedion", "Paucar", 40));

        List<Person> personList2 = new ArrayList<>();
        personList2.add(new Person(1, "Jedion", "Paucar", 30));
        personList2.add(new Person(2, "Maria", "Lucia", 60));
        personList2.add(new Person(3, "Jedion", "Paucar", 40));


        List<Sale> sales =  new ArrayList<>();
        sales.add(new Sale(1, LocalDateTime.now()));

        Flux<Person> flux1 = Flux.fromIterable(personList);
        Flux<Person> flux2 = Flux.fromIterable(personList2);
        Flux<Sale> flux3 = Flux.fromIterable(sales);

        flux1.zipWith(flux1, (p1,p2)-> String.format("%s %s", p1,p2))
                .subscribe(c->log.info(c));
    }
}
