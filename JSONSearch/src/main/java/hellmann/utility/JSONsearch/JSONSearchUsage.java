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

        Boolean bool = JSONSearch.searchForBoolean(json, "simple");
        System.out.println("Boolean = " + bool);

        ArrayList<Object> myList = JSONSearch.searchForJSONArrays(json);
        System.out.println(myList.toArray());

    }

    /**
     * Creates a String from the contents of a file.
     */
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
