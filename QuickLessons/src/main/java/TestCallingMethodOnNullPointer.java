public class TestCallingMethodOnNullPointer {

    public static void main(String[] args) {
        System.out.println("Hello");

        //The next line causes a NullPointerException.
        //myMethod().toString();

    }

    public static Object myMethod(){

        Object mine = null;
        return mine;
    }
}
