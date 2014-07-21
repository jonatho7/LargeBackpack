import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestTheNameOfAJSONObject {
    public static void main(String[] args) throws IOException {
        //Create the JSON object.
        String content = readFile("resources/test4.json", StandardCharsets.UTF_8);
        JSONObject originalJSON = new JSONObject(content);

        JSONArray rotateJSON = originalJSON.getJSONArray("rotate");
        String mine = rotateJSON.getString(0);
        Integer mine2 = rotateJSON.getInt(1);
        Integer mine3 = rotateJSON.getInt(2);
        Double mine4 = rotateJSON.getDouble(2);
        Boolean mine5 = rotateJSON.getBoolean(3);
        Object mine6 = rotateJSON.get(3);
        System.out.println(mine);
        System.out.println(mine2);
        System.out.println(mine3);
        System.out.println(mine4);
        System.out.println(mine5);
        System.out.println(mine6.getClass());
    }

    /**
     * Creates a String from the contents of a file.
     */
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
