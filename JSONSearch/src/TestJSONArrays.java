import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestJSONArrays {
    public static void main(String[] args) throws IOException {
        //Create the JSON object.
        String content = readFile("resources/test6.json", StandardCharsets.UTF_8);
        JSONObject originalJSON = new JSONObject(content);
        JSONArray rotateJSON = originalJSON.getJSONArray("rotate");

        //Attempt to get a JSONArray from a JSONArray. This attempt fails.
        JSONArray mineArray = rotateJSON.getJSONArray(0);

    }

    /**
     * Creates a String from the contents of a file.
     */
    public static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
