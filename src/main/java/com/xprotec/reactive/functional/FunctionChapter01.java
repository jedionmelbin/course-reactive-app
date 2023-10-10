package com.xprotec.reactive.functional;

import com.xprotec.reactive.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionChapter01 {
    final List<Person> personList = new ArrayList<>();

    public void program1() {


        personList.add(new Person(1, "Jedion", "Paucar", 30));
        personList.add(new Person(2, "Maria", "Lucia", 60));
        personList.add(new Person(3, "Jedion", "Paucar", 40));
        personList.add(new Person(4, "Andrea", "Perez", 45));

        List<Person> peoples = Arrays
                .asList(new Person(5, "Antuanne", "Horna", 30),
                        new Person(6, "jose", "Fernandes", 18),
                        new Person(7, "Ingrid", "Lopez", 32)
                );

        List<Person> newPeopleList = Stream
                .concat(personList.stream(), peoples.stream()).toList();

        List<Person> new1PeopleList = Stream.of(personList, peoples)
                .flatMap(Collection::stream).toList();

        personList.addAll(new1PeopleList);

        // Lambda method function anonymous
        newPeopleList.forEach(person -> System.out.println(person.getFirstName()));
        //
    }

    //Callback hall, filters stream
    private void program2() {
        // Lambda method function anonymous
        //personList.forEach(person -> System.out.println(person.getFirstName()));

        // personList.parallelStream();

        Function<String, String> predicates = name -> "code";

        List<Person> peoples = personList.stream()
                .filter(person -> person.getAge() >= 30)
                .toList();

        printList(peoples);
    }

    /// Operator Map and filters
    public void program3() {
        List<Integer> peoples = personList.stream().map(person -> {
            person.setAge(person.getAge() * 2);
            return person.getAge();
        }).toList();

        printList(peoples);
    }

    public void program4() {
        Comparator<Person> byNameAsc = (Person obj1, Person obj2) -> obj1.getFirstName()
                .compareTo(obj2.getFirstName());

        List<Person> peoples = personList.stream().sorted(byNameAsc)
                .toList();

        printList(personList);
    }

    //Person
    public void program5() {
        Comparator<Person> byNameAsc = (Person obj1, Person obj2) -> obj1.getFirstName()
                .compareTo(obj2.getFirstName());

        List<Person> peoples = personList.stream()
                .sorted(byNameAsc)
                .toList();

        //No evalua TODO el stream, busca alguna coencidencia anymatch
        boolean exist = peoples.stream()
                .anyMatch(c -> c.getFirstName().equals("Jedion"));
        if (exist) {
            System.out.println("Continue");
        }

        //Todos coencidan all
        boolean peope = peoples.stream()
                .allMatch(c -> c.getFirstName().equals("Jedion"));

        if (peope) {
            System.out.println("Todos son");
        }

        boolean otherBool = peoples.stream()
                .noneMatch(c -> c.getFirstName().equals("Jedion"));
        if (otherBool) {
            System.out.println("Plush");
        }

        String firstName = "Hola mudno";
        System.out.println(firstName.startsWith("H"));
        printList(personList);
    }

    // Skip, limit, collectors
    public void program6() {

        List<Person> people = personList.stream()
                .skip(1)
                .limit(10)
                .collect(Collectors.toList());
        //.toList();
    }

    //Group by
    public void program7() {
        Map<Integer, List<Person>> people = personList.stream()
                .filter(c -> c.getAge() > 20)
                .collect(Collectors.groupingBy(Person::getAge));


        Map<Integer, Long> groupByCount = personList.stream()
                .filter(c -> c.getAge() > 20)
                .collect(Collectors.groupingBy(c -> c.getAge(), Collectors.counting()));

        //.toList();
        System.out.println(people);
    }

    //Group by and sum
    public void program8() {
        Map<Integer, List<Person>> people = personList.stream()
                .filter(c -> c.getAge() > 20)
                .collect(Collectors.groupingBy(Person::getAge));


        Map<Object, Double> groupBySum = personList.stream()
                .filter(c -> c.getAge() > 20)
                .collect(Collectors.groupingBy(c -> c.getAge(),
                        Collectors.summingDouble(c -> c.getAge())));

        //.toList();
        System.out.println(people);
    }

    //Group by and sum, average
    public void program9() {
        DoubleSummaryStatistics people = personList.stream()
                .collect(Collectors.summarizingDouble(c -> c.getAge()));


        //.toList();
        System.out.println(people);
    }

    //Reduce
    public void program10() {
        Optional<Integer> people = personList.stream()
                .map(Person::getAge)
                .reduce(Integer::sum);

        //.toList();
        System.out.println(people.orElseThrow());
    }

    public static int getAge(LocalDate value) {
        return Period.between(value, LocalDate.now()).getYears();
        //return value.getMonth().getValue();
    }

    public void printList(List<?> list) {
        list.forEach(System.out::println);
    }
}
