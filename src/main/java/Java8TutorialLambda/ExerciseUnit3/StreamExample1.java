package Java8TutorialLambda.ExerciseUnit3;

import Java8TutorialLambda.ExerciseUnit1.Person;

import java.util.Arrays;
import java.util.List;

public class StreamExample1 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Caroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlote", "Bronte", 45),
                new Person("Matthew", "Arnold", 51)
        );
        people.stream().filter(p -> p.getLastName().startsWith("C")).forEach(p -> System.out.println(p.getFirstName()));

        long count=people.parallelStream().filter(p -> p.getLastName().startsWith("C")).count();
        System.out.println(count);
    }
}
