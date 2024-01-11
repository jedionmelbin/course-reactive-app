package com.xprotec.reactive.reactive;

import com.xprotec.reactive.model.Comment;
import com.xprotec.reactive.model.Person;
import com.xprotec.reactive.model.PersonComment;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Chapter10 {

    public Person getPerson() {
        return new Person(1, "Jedion Melbin", "Jedion", 20);
    }

    public void userCommentZip() {
        Mono<Person> personMono = Mono.fromCallable(() -> getPerson());

        Mono<Comment> commentMono = Mono.fromCallable(() -> {
            Comment comment = new Comment();
            comment.addComments("Hello comment 1");
            comment.addComments("Hello comment 2");
            comment.addComments("Hello comment 3");
            comment.addComments("Hello comment 4");
            return comment;
        });

        personMono.flatMap(person -> commentMono.map(comment ->
                new PersonComment(person, comment))).subscribe(personComment -> {
            log.info(personComment.toString());
        });

        Mono<PersonComment> personCommentMono =
                personMono.zipWith(commentMono, PersonComment::new);

        Mono<PersonComment> userComment = personMono.zipWith(commentMono, (user, comment) ->
                new PersonComment(user, comment));

        personCommentMono.subscribe(c -> {
            log.info(c.toString());
        });
    }

    public void userCommentFormTwo() {
        Mono<Person> personMono = Mono.fromCallable(() -> getPerson());

        Mono<Comment> commentMono = Mono.fromCallable(() -> {
            Comment comment = new Comment();
            comment.addComments("Hello comment 1");
            comment.addComments("Hello comment 2");
            comment.addComments("Hello comment 3");
            comment.addComments("Hello comment 4");
            return comment;
        });

        personMono.flatMap(person -> commentMono.map(comment ->
                        new PersonComment(person, comment)))
                .subscribe(personComment -> {
                    log.info(personComment.toString());
                });

        Mono<PersonComment> personCommentMono =
                personMono.zipWith(commentMono)
                        .map(tuple -> {
                            Person person = tuple.getT1();
                            Comment comment = tuple.getT2();
                            return new PersonComment(person, comment);
                        });


        personCommentMono.subscribe(c -> {
            log.info(c.toString());
        });
    }
}
