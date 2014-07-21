package hellmann.utility;

import org.json.JSONObject;
import org.securegraph.type.GeoPoint;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GeoLocationUtilUsage {
    public static void main(String[] args) {
        System.out.println("Hello");

        //Create the JSON object.
        String content = null;
        try {
            content = readFile("resources/asia_mov_changed.json", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject(content);

        GeoPoint geoPoint = GeoLocationUtil.extractGeoLocationFromJSON(json);
        System.out.println(geoPoint);
        System.out.println(geoPoint.getAltitude());

    }

    /**
     * Creates a String from the contents of a file.
     */
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
