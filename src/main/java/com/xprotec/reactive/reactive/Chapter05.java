package com.xprotec.reactive.reactive;

import com.xprotec.reactive.model.Customer;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Chapter05 {
    public void program1FlatMap() {

        List<String> persons = new ArrayList<>();
        persons.add("Maria");
        persons.add("Pedro");
        persons.add("Juan");
        persons.add("Bruce");
        persons.add("Antuanne");

        // Concatena en mismo flujo de datos
        Flux.fromIterable(persons)
                .map(cu -> new Customer(1, null, cu.toUpperCase(), "Last N"))
                //.filter(customer -> customer.getLastName().equals("Angelica"))
                .flatMap(customer -> {
                    if (customer.getFirstName().equals("Pedro")) {
                        return Mono.just(customer);
                    } else {
                        return Mono.empty();
                    }
                })
                .map(customer -> {
                    String fullName = customer.getFirstName() + " " + customer.getLastName();
                    //return name.toUpperCase();
                    customer.setFullName(fullName);
                    return customer;
                }).subscribe(customer -> {
                    log.info(customer.getFullName());
                });
    }

    public void program1Map() {
        List<String> persons = new ArrayList<>();
        persons.add("Maria");
        persons.add("Pedro");
        persons.add("Juan");
        persons.add("Bruce");
        persons.add("Antuanne");

        Flux<String> userFlux = Flux.fromIterable(persons);
    }
}
