import org.json.JSONStringer;

public class TestArrayWithinArray {
    public static void main(String[] args) {
        String string = new JSONStringer()
                .object()
                .key("Hi")
                .array()
                .array()
                .value(true)
                .value("ying")
                .value(90)
                .endArray()
                .object()
                .key("rotate")
                .value(20)
                .endObject()
                .endArray()
                .endObject()
                .toString();
        System.out.println("string = " + string);

        String other = new JSONStringer()
                .object()
                .key("Key")
                .value("Mine")
                .endObject()
                .toString();
        System.out.println("other = " + other);

    }
}
