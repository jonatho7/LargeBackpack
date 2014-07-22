package hellmann.utility.editing;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

public class JSONSearchGeneric {

    public static JSONArray searchForJSONArray(JSONObject json, String name) {
        JSONArray value = (JSONArray) recursiveSearch(null, json, name, JSONArray.class);
        return value;
    }

    public static Integer searchForInteger(JSONObject json, String name) {
        Integer value = (Integer) recursiveSearch(null, json, name, Integer.class);
        return value;
    }

    public static String searchForString(JSONObject json, String name) {
        String value = (String) recursiveSearch(null, json, name, String.class);
        return value;
    }

    //Works for Integer and String so far.
    //TODO. Make this work for searching for JSONObject and JSONArrays too would be pretty cool.
    //JSONArrays break down into many different things. Am starting to incorporate this.
    private static Object recursiveSearch(String objectToReduceName, Object objectToReduceValue, String requestedName, Class requestedObjectType) {
        if (objectToReduceValue == null) {
            return null;
        } else if (objectToReduceValue.getClass() == JSONArray.class) {
            //Then we have a JSONArray. Is this what we wanted to return?
            if (requestedName.equals(objectToReduceName) && objectToReduceValue.getClass() == requestedObjectType) {
                //Then we have a JSONArray with the requested name and return type. return this.
                return objectToReduceValue;
            }
            //Otherwise, Break down the JSONArray into JSONObjects.
            JSONArray jsonArray = (JSONArray) objectToReduceValue;
            for (int i = 0; i < jsonArray.length(); i++) {
                Object tempObject = jsonArray.get(i);
                Object nullableValue = recursiveSearch(null, tempObject, requestedName, requestedObjectType);
                if (nullableValue != null) {
                    return nullableValue;
                }
            }
        } else if (objectToReduceValue.getClass() == JSONObject.class) {
            //Then we have a JSONObject. Is this what we wanted to return?
            if (requestedName.equals(objectToReduceName) && objectToReduceValue.getClass() == requestedObjectType) {
                //Then we have a JSONObject with the requested name and return type. return this.
                return objectToReduceValue;
            }
            //Otherwise, Break down the JSONObject.
            JSONObject jsonObject = (JSONObject) objectToReduceValue;
            JSONArray names = jsonObject.names();
            JSONArray values = jsonObject.toJSONArray(names);
            for (int i = 0; i < names.length(); i++) {
                //Get whatever is inside the JSONObject, and call the search method again.
                String name = (String) names.get(i);
                Object value = values.get(i);
                Object nullableValue = recursiveSearch(name, value, requestedName, requestedObjectType);
                if (nullableValue != null) {
                    return nullableValue;
                }
            }
        } else if (objectToReduceValue.getClass() == Double.class) {
            if (requestedName.equals(objectToReduceName) && objectToReduceValue.getClass() == requestedObjectType) {
                if (!Double.isNaN((Double) objectToReduceValue)) {
                    return objectToReduceValue;
                }
            }
        } else if (objectToReduceValue.getClass() == Integer.class) {
            //Then we have an Integer value for a name:value pair.
            if (requestedName.equals(objectToReduceName) && objectToReduceValue.getClass() == requestedObjectType) {
                return objectToReduceValue;
            }
        } else if (objectToReduceValue.getClass() == String.class) {
            if (requestedName.equals(objectToReduceName) && objectToReduceValue.getClass() == requestedObjectType) {
                return objectToReduceValue;
            }
        } else if (objectToReduceValue.getClass() == Boolean.class) {
            if (requestedName.equals(objectToReduceName) && objectToReduceValue.getClass() == requestedObjectType) {
                return objectToReduceValue;
            }
        } else if (objectToReduceValue.getClass() == Long.class) {
            if (requestedName.equals(objectToReduceName) && objectToReduceValue.getClass() == requestedObjectType) {
                return objectToReduceValue;
            }
        } else if (objectToReduceValue.getClass() == JSONString.class) {
            //if (new Thingy() instanceof JSONString){
            //}
            //if (requestedObjectType instanceof JSONString){
            //
            //}
        }


        //No results found. Return null.
        return null;
    }

}
