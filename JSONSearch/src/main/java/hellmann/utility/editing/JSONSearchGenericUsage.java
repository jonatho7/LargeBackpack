package hellmann.utility.editing;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONSearchGenericUsage {
    public static void main(String[] args) throws Exception {
        method0();

    }

    public static void method0() throws Exception{
        //Create the JSON object.
        JSONObject json = new JSONObject();
        int mine = 40;
        json.put("what", mine);
        System.out.println(json);
        System.out.println(json.get("what").getClass());

        Integer rotate = JSONSearchGeneric.searchForInteger(json, "what");
        System.out.println("rotate = " + rotate);
        Double rot = JSONSearchGeneric.searchForDouble(json, "what");
        System.out.println("rot = " + rot);

        Long longRot = JSONSearchGeneric.searchForLong(json, "what");
        System.out.println("longRot = " + longRot);
    }

    public static void method1() throws Exception{
        //Create the JSON object.
        String content = readFile("resources/asia_mov.json", StandardCharsets.UTF_8);
        JSONObject json = new JSONObject(content);

        //Search in the JSON object.
        double startTime = System.nanoTime();
        Integer rotate = JSONSearchGeneric.searchForInteger(json, "rotate");
        double endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000000);
        System.out.println("rotate = " + rotate);

        String profile = JSONSearchGeneric.searchForString(json, "profile");
        System.out.println("profile = " + profile);

        JSONObject jsonObject = JSONSearchGeneric.searchForJSONObject(json, "tags");
        System.out.println("format = " + jsonObject);

        JSONArray jsonArray = JSONSearchGeneric.searchForJSONArray(json, "mine");
        System.out.println("mine = " + jsonArray);

        Boolean bool = JSONSearchGeneric.searchForBoolean(json, "fine");
        System.out.println("fine = " + bool);

    }

    /**
     * Creates a String from the contents of a file.
     */
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
