package Java8TutorialLambda.ExerciseUnit3;

import Java8TutorialLambda.ExerciseUnit1.Person;

import java.util.Arrays;
import java.util.List;

public class CollectionInterationExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Caroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlote", "Bronte", 45),
                new Person("Matthew", "Arnold", 51)
        );

        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }

        for (Person p : people) {
            System.out.println(p);
        }
        System.out.println("Lambda");
        people.forEach(p -> System.out.println(p));

        System.out.println("Method Reference");
        people.forEach(System.out::println);
    }
}
