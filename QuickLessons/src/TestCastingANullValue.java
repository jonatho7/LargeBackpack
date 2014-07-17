public class TestCastingANullValue {
    public static void main(String[] args) {
        //Casting null to any Object type does not cause a null pointer exception.
        //Reference: http://stackoverflow.com/questions/18723596/no-exception-while-type-casting-with-a-null-in-java
        Integer mine = (Integer) myMethod();
        System.out.println(mine);

        //Assigning an int to null causes a null pointer exception.
        int mine2 = myMethod2();
    }

    public static Object myMethod(){
        return null;
    }

    public static Integer myMethod2(){
        return null;
    }
}
