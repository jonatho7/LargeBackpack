package hellmann.utility.JSONsearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONSearch {

    public static Boolean searchForBoolean(JSONObject json, String name) {
        Boolean value = (Boolean) recursiveSearch(null, json, name, Boolean.class);
        return value;
    }

    public static String searchForString(JSONObject json, String name) {
        String value = (String) recursiveSearch(null, json, name, String.class);
        return value;
    }

    /**
     * Recursively search for a double value within all hierarchies of a JSONObject.
     * Will return results for a double, long, or int value, not strictly just double values.
     * Will return null if a result is not found.
     *
     * @param json The JSONObject to search within.
     * @param name
     * @return
     */
    public static Double searchForDouble(JSONObject json, String name) {
        Object value = recursiveSearch(null, json, name, Double.class);
        if (value != null) {
            if (value.getClass() == Double.class) {
                return new Double((Double) value);
            } else if (value.getClass() == Long.class) {
                return new Double(((Long) value).doubleValue());
            } else if (value.getClass() == Integer.class) {
                return new Double(((Integer) value).doubleValue());
            }
        }

        return null;
    }

    /**
     * Search for a long value. Will return results for a long or int value.
     *
     * @param json
     * @param name
     * @return
     */
    public static Long searchForLong(JSONObject json, String name) {
        Object value = recursiveSearch(null, json, name, Long.class);
        if (value != null) {
            if (value.getClass() == Long.class) {
                return new Long((Long) value);
            } else if (value.getClass() == Integer.class) {
                return new Long(((Integer) value).longValue());
            }
        }

        return null;
    }

    /**
     * Search for an int value.
     *
     * @param json
     * @param name
     * @return
     */
    public static Integer searchForInteger(JSONObject json, String name) {
        Integer value = (Integer) recursiveSearch(null, json, name, Integer.class);
        return value;
    }

    public static JSONObject searchForJSONObject(JSONObject json, String name) {
        JSONObject value = (JSONObject) recursiveSearch(null, json, name, JSONObject.class);
        return value;
    }

    public static JSONArray searchForJSONArray(JSONObject json, String name) {
        JSONArray value = (JSONArray) recursiveSearch(null, json, name, JSONArray.class);
        return value;
    }

    public static JSONArray searchForJSONArray(JSONObject json, Object[] values){


        return null;
    }

    public static ArrayList<Object> searchForJSONArray(JSONObject json){
        return null;
    }

    private static Object recursiveSearch(String objectToReduceName, Object objectToReduceValue, String requestedName, Class requestedObjectType) {
        if (objectToReduceValue == null) {
            return null;
        } else if (objectToReduceValue.getClass() == JSONArray.class) {
            //Then we have a JSONArray. Is it one without a name?
            if (requestedName == null){

            }
            //Then we have a JSONArray with a name. Is this what we wanted to return?
            if (requestedName.equals(objectToReduceName) && objectToReduceValue.getClass() == requestedObjectType) {
                //Then we have a JSONArray with the requested name and return type. return this.
                return objectToReduceValue;
            }
            //Otherwise, Break down the JSONArray.
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
            if (requestedObjectType == Double.class) {
                if (requestedName.equals(objectToReduceName)) {
                    //Check if objectToReduceValue is a valid double.
                    if (!Double.isNaN((Double) objectToReduceValue)) {
                        return objectToReduceValue;
                    }
                }
            }
        } else if (objectToReduceValue.getClass() == Long.class) {
            if (requestedObjectType == Long.class ||
                    requestedObjectType == Integer.class) {
                if (requestedName.equals(objectToReduceName)) {
                    return objectToReduceValue;
                }
            }
        } else if (objectToReduceValue.getClass() == Integer.class) {
            if (requestedObjectType == Integer.class ||
                    requestedObjectType == Double.class ||
                    requestedObjectType == Long.class) {
                if (requestedName.equals(objectToReduceName)) {
                    return objectToReduceValue;
                }
            }
        } else if (objectToReduceValue.getClass() == String.class) {
            if (requestedObjectType == String.class) {
                if (requestedName.equals(objectToReduceName)) {
                    return objectToReduceValue;
                }
            }
        } else if (objectToReduceValue.getClass() == Boolean.class) {
            if (requestedObjectType == Boolean.class) {
                if (requestedName.equals(objectToReduceName)) {
                    return objectToReduceValue;
                }
            }
        }

        //No results found. Return null.
        return null;
    }

}
