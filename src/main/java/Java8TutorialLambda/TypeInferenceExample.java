package Java8TutorialLambda;

public class TypeInferenceExample {
    public static void main(String[] args) {
        stringLengthLambda myLambda = s -> s.length();
        System.out.println(myLambda.getLength("Van Viet Vuong"));
    }

    interface stringLengthLambda{
        int getLength(String s);
    }
}
