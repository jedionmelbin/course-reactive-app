package com.xprotec.reactive.reactive;

import com.xprotec.reactive.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Chapter02 {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void program1() {
        Flux<Customer> names = Flux
                .just("Angelica", "Andrea", "Lina", "xs", "Juan")
                //map(m -> m.toUpperCase())
                .map(cu -> new Customer(1, null, cu.toUpperCase(), "Last N"))
                .filter(customer -> customer.getLastName().equals("Angelica"))
                .doOnNext(e -> {
                    if (e == null) {
                        throw new RuntimeException("The value is empty");
                    } else {
                        System.out.println(e);
                    }
                }).map(customer -> {
                    String fullName = customer.getFirstName() + " " + customer.getLastName();
                    //return name.toUpperCase();
                    customer.setFullName(fullName);
                    return customer;
                });

        names.subscribe(e -> logger.info(e.getFullName()),
                error -> logger.error(error.getMessage()),
                new Runnable() {
                    @Override
                    public void run() {
                        logger.info("The process is completed");
                    }
                });
    }
}
