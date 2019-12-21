package Java8TutorialLambda.ExerciseUnit1;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [First Name =" + firstName + ",Last Name = " + lastName + ", Age = " + age + "]";
    }
}