package testWildcards;

import java.util.ArrayList;
import java.util.List;

public class TestUsingWildcards {
    public static void main(String[] args) {
        System.out.println("Hello.");
        ArrayList<Last5ApplicationRows> myList = new ArrayList<Last5ApplicationRows>();
        getRows(myList);
    }

    //Use of wildcards in this method. Can pass in a List<> with any type that extends testWildcards.HomeTableRows.
    public static void getRows(List<? extends HomeTableRows> myList){

    }


}
