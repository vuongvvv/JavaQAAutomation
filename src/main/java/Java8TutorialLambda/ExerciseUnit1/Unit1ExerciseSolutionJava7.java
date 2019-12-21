package Java8TutorialLambda.ExerciseUnit1;

import java.util.*;

public class Unit1ExerciseSolutionJava7 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Caroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlote", "Bronte", 45),
                new Person("Matthew", "Arnold", 51)
        );

        //Step 1: Sort list by lastName
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        //Step 2: Create a method that print all elements in the list
        System.out.println("Step 2: Create a method that print all elements in the list");
        printAll(people);

        //Step 3: Create a method that print all elements that have the last name begin with C
        System.out.println("Step 3: Create a method that print all elements that have the last name begin with C");
        printConditionally(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return p.getLastName().startsWith("C");
            }
        });

        printConditionally(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return p.getFirstName().startsWith("C");
            }
        });
    }

    private static void printConditionally(List<Person> people, Condition condition) {
        for (Person p : people) {
            if (condition.test(p))
                System.out.println(p);
        }
    }

    private static void printAll(List<Person> people) {
        for (Person p : people) {
            System.out.println(p);
        }
    }
}

interface Condition{
    boolean test(Person p);
}