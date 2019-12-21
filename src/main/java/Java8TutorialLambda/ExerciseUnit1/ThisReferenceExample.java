package Java8TutorialLambda.ExerciseUnit1;

public class ThisReferenceExample {
    public static void main(String[] args) {
        ThisReferenceExample thisReferenceExample = new ThisReferenceExample();
//        thisReferenceExample.doProcess(10, i -> {
//            System.out.println("Value of i is: " + i);
//            System.out.println(this);
//        });

        thisReferenceExample.execute();
    }

    public void doProcess(int i, Process process) {
        process.process(i);
    }

    public void execute(){
        doProcess(10, i -> {
            System.out.println("Value of i is: " + i);
            System.out.println(this);
        });
    }

//    public String toString(){
//        return "This is the main ThisReferenceExample class instance";
//    }
}
