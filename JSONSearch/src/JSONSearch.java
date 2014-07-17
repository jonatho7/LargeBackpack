import org.json.JSONArray;
import org.json.JSONObject;

public class JSONSearch {
    public static Integer searchInElement(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == JSONArray.class) {
            //Then we have a JSONArray. Break down the JSONArray into JSONObjects.
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject tempObj = jsonArray.getJSONObject(i);
                Integer temp = searchInElement(tempObj);
                if (temp != null) {
                    return temp;
                }
            }
        } else if (obj.getClass() == JSONObject.class) {
            //Then we have a JSONObject. Determine what the contents are.
            JSONObject json = (JSONObject) obj;
            String[] names = JSONObject.getNames(json);
            for (int i = 0; i < names.length; i++) {
                //Get whatever is inside the JSONObject.
                Object tempObject = json.get(names[i]);
                if (tempObject.getClass() == JSONArray.class) {
                    //Then break down the array using the searchInElement method.
                    Integer temp = searchInElement(tempObject);
                    if (temp != null) {
                        return temp;
                    }
                } else if (tempObject.getClass() == JSONObject.class) {
                    //Then break down the object using the searchInElement method.
                    Integer temp = searchInElement(tempObject);
                    if (temp != null) {
                        return temp;
                    }
                } else {
                    //Then the Object is neither a JSONArray or JSONObject. This means we have a value.
                    if ("rotate".equals(names[i])) {
                        //Then we have found the correct name: value pair. Get the value.
                        Double rotate = json.optDouble(names[i]);
                        if (!Double.isNaN(rotate)) {
                            //Then the value was valid. Return results.
                            return rotate.intValue();
                        }
                    }
                }
            }
        }

        //No results found. Return null.
        return null;
    }
}
