package Java8TutorialLambda.ExerciseUnit1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StandardFunctionalityUnit1ExerciseSolutionJava8 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Caroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlote", "Bronte", 45),
                new Person("Matthew", "Arnold", 51)
        );

        //Step 1: Sort list by lastName
        Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
        //Step 2: Create a method that print all elements in the list
        System.out.println("Step 2: Create a method that print all elements in the list");
        performConditionally(people, p -> true, p -> System.out.println(p));

        //Step 3: Create a method that print all elements that have the last name begin with C
        System.out.println("Step 3: Create a method that print all elements that have the last name begin with C");
        performConditionally(people, p -> p.getLastName().startsWith("C"),p -> System.out.println(p));

        performConditionally(people, p -> p.getFirstName().startsWith("C"),p -> System.out.println(p.getFirstName()));
    }

    private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person p : people) {
            if (predicate.test(p))
                consumer.accept(p);
        }
    }
}
