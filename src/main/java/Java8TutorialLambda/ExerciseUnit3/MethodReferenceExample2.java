package Java8TutorialLambda.ExerciseUnit3;

import Java8TutorialLambda.ExerciseUnit1.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReferenceExample2 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Caroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlote", "Bronte", 45),
                new Person("Matthew", "Arnold", 51)
        );


        //Step 2: Create a method that print all elements in the list
        System.out.println("Step 2: Create a method that print all elements in the list");
        performConditionally(people, p -> true, System.out::println);
    }

    private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person p : people) {
            if (predicate.test(p))
                consumer.accept(p);
        }
    }
}
