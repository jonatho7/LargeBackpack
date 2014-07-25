import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class TestJSONObjectKeySet {
    public static void main(String[] args) {
        System.out.println("Hello");

        //Create the JSON object.
        String content = null;
        try {
            content = readFile("resources/asia_mov_changed2.json", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject(content);

        System.out.println( json.keySet() );
    }

    /**
     * Creates a String from the contents of a file.
     */
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
