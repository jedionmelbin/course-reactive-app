package com.xprotec.app;

import com.xprotec.app.combination.Combination;
import com.xprotec.app.conditional.Conditional;
import com.xprotec.app.creation.Creation;
import com.xprotec.app.error.Error;
import com.xprotec.app.filters.Filters;
import com.xprotec.app.math.Mathematical;
import com.xprotec.app.model.Person;
import com.xprotec.app.tranform.Transformation;
import io.reactivex.rxjava3.core.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CourseReactiveAppApplication implements CommandLineRunner {
    private static  final Logger log = LoggerFactory.getLogger(CourseReactiveAppApplication.class);

    public void reactor(){
        Mono.just(new Person(1,"Jedion","Paucar",30))
                //.doOnNext(c->log.info("[reactor] {}", c.toString()));
                .doOnNext(c-> {
                    log.info("[reactor] {}", c.toString());
                }).subscribe(c->log.info("[reactor] {}", c.toString()));
    }

    public void  rxJava3(){
        Observable.just(new Person(1,"Jedion","Paucar",30))
                .doOnNext(c->log.info("[RxJava3] {}", c.toString()));
                //.subscribe(c->log.info("[RxJava3] {}", c.toString()));
    }

    public void mono(){
        Mono.just(new Person(1,"Jedion","Paucar",30))
                .subscribe(c-> log.info("[Mono] {}", c.toString()));
    }

    public void flux(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",30));
        personList.add(new Person(2,"Maria","Lucia",60));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux.fromIterable(personList).subscribe(c-> log.info("[Flux] {} ", c.toString()));
    }

    public void fluxToMono(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Jedion","Paucar",30));
        personList.add(new Person(2,"Maria","Lucia",60));
        personList.add(new Person(3,"Jedion","Paucar",40));

        Flux<Person> personFlux =   Flux.fromIterable(personList);
        personFlux.collectList().subscribe(c-> log.info(c.toString()));

    }

    //Operaciones de creacion
    //Operador de transformacion


    public static void main(String[] args) {
        SpringApplication.run(CourseReactiveAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        reactor();
        rxJava3();
        mono();
        flux();
        fluxToMono();

        /// Creations
        System.out.printf("CREATION");
        Creation creation = new Creation();
        creation.range();
        creation.repeat();

        //Tranform
        System.out.println("TRANFORMATION");
        Transformation transformation = new Transformation();
        transformation.map();
        transformation.flatMap();

        System.out.println("GROUP BY ");
        transformation.groupBy();

        System.out.println("OPERATOR FILTER");

        Filters  filters = new Filters();
        filters.filters();
        filters.distinct();

        System.out.printf("TAKE LAST \n");
        filters.takes();

        System.out.println("CONBINATION ");
        Combination combination = new Combination();
        combination.merge();

        System.out.println("ZIP OPERATOR");
        combination.zip();
        combination.zipWith();

        System.out.println("ERROR");
        Error error = new Error();
        //error.errorMap();


        System.out.println("CONDITIONAL");
        Conditional conditional = new Conditional();
        conditional.takeUntil();

        System.out.println("TIME OUT");
        conditional.timeoutCond();

        System.out.println("MATHEMATICAL");

        Mathematical mathematical = new Mathematical();
        mathematical.average();

    }




}
