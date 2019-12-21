package Java8TutorialLambda;

public class Greeter {
    public void greet(Greeting greeting){
//        System.out.println("Hello World!");
        greeting.perform();
    }


    public static void main(String[] args){
        Greeter greeter = new Greeter();
//        HelloWorldGreeting helloWorldGreeting = new HelloWorldGreeting();
//        greeter.greet(helloWorldGreeting);

        Greeting innerClass = new Greeting() {
            @Override
            public void perform() {
                System.out.println("Hello World!");
            }
        };

//        innerClass.perform();
        greeter.greet(innerClass);
        Greeting myLambda = () -> System.out.println("Hello World!");
//        myLambda.perform();
//        MyLambdaAdd addFunction = (int a, int b) -> a + b;
        greeter.greet(myLambda);
    }
}
