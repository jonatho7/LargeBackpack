package hellmann.utility.JSONsearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONSearchUsage {
    public static void main(String[] args) throws Exception {
        method1();

    }

    public static void method0() throws Exception{
        //Create the JSON object.
        JSONObject json = new JSONObject();
        int mine = 40;
        json.put("what", mine);
        System.out.println(json);
        System.out.println(json.get("what").getClass());

        Integer rotate = JSONSearch.searchForInteger(json, "what");
        System.out.println("rotate = " + rotate);

        Double rot = JSONSearch.searchForDouble(json, "what");
        System.out.println("rot = " + rot);

        Long longRot = JSONSearch.searchForLong(json, "what");
        System.out.println("longRot = " + longRot);
    }

    public static void method1() throws Exception{
        //Create the JSON object.
        String content = readFile("resources/IMG_1894_mov.json", StandardCharsets.UTF_8);
        JSONObject json = new JSONObject(content);

        //Search in the JSON object.
        double startTime = System.nanoTime();
        Integer rotate = JSONSearch.searchForInteger(json, "rotate");
        double endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000000);
        System.out.println("Integer = " + rotate);

        Double doubleRot = JSONSearch.searchForDouble(json, "rotate");
        System.out.println("Double = " + doubleRot);

        Long longRot = JSONSearch.searchForLong(json, "rotate");
        System.out.println("Long = " + longRot);

        String profile = JSONSearch.searchForString(json, "profile");
        System.out.println("String = " + profile);

        JSONObject jsonObject = JSONSearch.searchForJSONObject(json, "tags");
        System.out.println("JSONObject = " + jsonObject);

        JSONArray jsonArray = JSONSearch.searchForJSONArray(json, "streams");
        System.out.println("JSONArray = " + jsonArray);

        Boolean bool = JSONSearch.searchForBoolean(json, "answer");
        System.out.println("Boolean = " + bool);

        ArrayList<JSONArray> myList = JSONSearch.searchForJSONArrays(json);
        System.out.println(myList.size() + " JSONArrays without a name");
        for(int i = 0; i < myList.size(); i++){
            System.out.println( ((JSONArray) myList.get(i)).toString(4));
        }

        ArrayList<JSONArray> myListing = JSONSearch.searchForJSONArrays(json, "cool");
        System.out.println(myListing.size() + " JSONArrays with the name cool");
        for(int i = 0; i < myListing.size(); i++){
            System.out.println( ((JSONArray) myListing.get(i)).toString(4));
        }

        ArrayList<JSONObject> jsonObjects = JSONSearch.searchForJSONObjects(json, "objective");
        System.out.println(jsonObjects.size() + " JSONObjects with the name objective");
        for(int i = 0; i < jsonObjects.size(); i++){
            System.out.println( ((JSONObject) jsonObjects.get(i)).toString(4));
        }

        ArrayList<Integer> integerList = JSONSearch.searchForIntegers(json, "rotate");
        System.out.println(integerList.size() + " Integers with the name rotate");
        for(int i = 0; i < integerList.size(); i++){
            System.out.println( integerList.get(i).toString());
        }

        ArrayList<Long> longsList = JSONSearch.searchForLongs(json, "rotate");
        System.out.println(longsList.size() + " Longs with the name rotate");
        for(int i = 0; i < longsList.size(); i++){
            System.out.println(  longsList.get(i).toString());
        }

        ArrayList<Double> doublesList = JSONSearch.searchForDoubles(json, "rotate");
        System.out.println(doublesList.size() + " Doubles with the name rotate");
        for(int i = 0; i < doublesList.size(); i++){
            System.out.println( doublesList.get(i).toString());
        }

        ArrayList<String> stringsList = JSONSearch.searchForStrings(json, "profile");
        System.out.println(stringsList.size() + " Strings with the name profile");
        for(int i = 0; i < stringsList.size(); i++){
            System.out.println( stringsList.get(i).toString());
        }

        ArrayList<Boolean> booleansList = JSONSearch.searchForBooleans(json, "answer");
        System.out.println(booleansList.size() + " Booleans with the name answer");
        for(int i = 0; i < booleansList.size(); i++){
            System.out.println( booleansList.get(i).toString());
        }

        ArrayList<Object> objectsList = JSONSearch.searchForObjects(json, "rotate");
        System.out.println(objectsList.size() + " Objects with the name rotate");
        for(int i = 0; i < objectsList.size(); i++){
            System.out.println( objectsList.get(i).toString());
        }

        Object myObject = JSONSearch.searchForObject(json, "rotate");
        System.out.println("The first Object with the name rotate found = " + myObject);



    }

    /**
     * Creates a String from the contents of a file.
     */
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
