package tests;

public class TestIllegalArgumentException {
    public static void main(String[] args) {
        System.out.println("Hello");
        myMethod(5);
        myMethod2();

        try {
            myMethod(null);
        } catch (IllegalArgumentException e) {
            myMethod2();
        }
    }

    public static Integer myMethod(Integer param){
        if (param == null){
            throw new IllegalArgumentException("The argument cannot be null");
        }

        return 5;
    }

    public static void myMethod2(){
        System.out.println("Feeling Fine.");
    }
}
