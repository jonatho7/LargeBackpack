package hellmann.utility.editing;

import org.json.JSONString;

public class Thingy implements JSONString {
    @Override
    public String toJSONString() {
        return "{\"name\": \"Hello, this is my JSON string\"}";
    }
}
