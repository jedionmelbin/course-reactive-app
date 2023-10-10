package com.xprotec.reactive.reactive;

import com.xprotec.reactive.model.Customer;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Chapter06 {

    //Convert flux to list
    public void program1() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Jedion Melbin", "Jedion", "Last N"));
        customers.add(new Customer(2, "Maria", "Maria Lucia", "Last N"));
        customers.add(new Customer(3, "Kelly", "Kelly", "Last N"));
        customers.add(new Customer(4, "Mariorit", "Mariorit", "Last N"));
        customers.add(new Customer(5, "Antuanne", "Antuanne", "Last N"));
        customers.add(new Customer(6, "Ingrid", "Ingrid", "Last N"));

        // Concatena en mismo flujo de datos
        Flux.fromIterable(customers)
                .map(cu -> cu.getFirstName().toUpperCase())
                //.filter(customer -> customer.getLastName().equals("Angelica"))
                .flatMap(customer -> {
                    if (customer.equals("Maria")) {
                        return Mono.just(customer);
                    } else {
                        return Mono.empty();
                    }
                })
                .map(String::toLowerCase).subscribe(customer -> {
                    log.info(customer);
                });
    }
}
