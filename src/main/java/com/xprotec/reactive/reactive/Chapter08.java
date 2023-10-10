package com.xprotec.reactive.reactive;

import com.xprotec.reactive.model.Comment;
import com.xprotec.reactive.model.Customer;
import com.xprotec.reactive.model.Person;
import com.xprotec.reactive.model.PersonComment;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Chapter08 {
    //Convert list to collection list

    public void userCommentFlatMaps() {
        Mono<Person> personMono = Mono.fromCallable(() -> getPerson());

        Mono<Comment> commentMono = Mono.fromCallable(() -> {
            Comment comment = new Comment();
            comment.addComments("Hello comment 1");
            comment.addComments("Hello comment 2");
            comment.addComments("Hello comment 3");
            comment.addComments("Hello comment 4");
            return comment;
        });

        personMono.flatMap(person -> commentMono.map(comment -> {
            return new PersonComment(person, comment);
        })).subscribe(personComment -> {
            log.info(personComment.toString());
        });

        Mono<PersonComment> personCommentMono =
                personMono.zipWith(commentMono, PersonComment::new);
        personCommentMono.subscribe(c -> {
            log.info(c.toString());
        });
    }

    public Person getPerson() {
        return new Person(1, "Jedion Melbin", "Jedion", 20);
    }


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
