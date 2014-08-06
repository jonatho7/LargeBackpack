import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestGetJSONValue {
    public static void main(String[] args) throws IOException {
        //Load the contents of a file into a String.
        String content = readFile("resources/video_info.json", StandardCharsets.UTF_8);

        //Create the JSON object.
        JSONObject json = new JSONObject(content);

        //Example 1. Get the rotate value from the JSON Object.
        Double rotate = null;
        try {
            JSONArray streamsJson = json.optJSONArray("streams");
            JSONObject streamsIndex0Json = streamsJson.optJSONObject(0);
            JSONObject tagsJson = streamsIndex0Json.optJSONObject("tags");
            rotate = tagsJson.optDouble("rotate");
        } catch (NullPointerException e){
            System.out.println("Could not retrieve the \"rotate\" value from the JSON object.");
        }
        System.out.println("rotate: " + rotate);


        //Example 2. Get the duration value from the JSON Object.
        Double duration = null;
        try {
            JSONObject formatJson = json.optJSONObject("format");
            duration = formatJson.optDouble("duration");
        } catch (NullPointerException e) {
            System.out.println("Could not retrieve the \"duration\" value from the JSON object.");
        }
        System.out.println("duration: " + duration);
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
