package com.xprotec.reactive.reactive;

import com.xprotec.reactive.model.Customer;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Chapter07 {

    //Convert list to collection list
    public void program1() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Jedion Melbin", "Jedion", "Last N"));
        customers.add(new Customer(2, "Maria", "Maria Lucia", "Last N"));
        customers.add(new Customer(3, "Kelly", "Kelly", "Last N"));
        customers.add(new Customer(4, "Mariorit", "Mariorit", "Last N"));
        customers.add(new Customer(5, "Antuanne", "Antuanne", "Last N"));
        customers.add(new Customer(6, "Ingrid", "Ingrid", "Last N"));

        Flux.fromIterable(customers)
                .collectList()
                .subscribe(customers1 -> {
                    customers1.forEach(c -> {
                        System.out.println(c.toString());
                    });
                    log.info(customers.toString());
                });
    }
}
