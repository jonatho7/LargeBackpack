public class TestClassType {
    public static void main(String[] args) {


        System.out.println(String.class);
        System.out.println("a".getClass());
        System.out.println(String.class.getClass());
        System.out.println();

        myMethod(int[].class);

        myMethod2(String.class);

        Class genericClass = String.class;
        Class<String> stringClassExample = (Class<String>) genericClass;
        System.out.println(stringClassExample);
        System.out.println(stringClassExample.getClass());

    }

    public static void myMethod(Class<int[]> classType) {
        System.out.println(classType.getClass());
    }

    public static void myMethod2(Class myClass) {
        if (myClass == String.class) {
            System.out.println("myClass == String.class");
        }
    }


}
