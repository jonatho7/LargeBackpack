package hellmann.utility.editing;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONSearchGenericUsage {
    public static void main(String[] args) throws IOException {
        //Create the JSON object.
        String content = readFile("resources/test7.json", StandardCharsets.UTF_8);
        JSONObject originalJSON = new JSONObject(content);

        //Search in the JSON object.
        Integer rotate = JSONSearchGeneric.searchForInteger(originalJSON, "rotate");
        System.out.println("rotate = " + rotate);

        String profile = (String) JSONSearchGeneric.searchForString(originalJSON, "profile");
        System.out.println("profile = " + profile);
    }

    /**
     * Creates a String from the contents of a file.
     */
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
