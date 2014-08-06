package hellmann.utility.JSONsearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONSearch {

    public static Object searchForObject(JSONObject json, String name) {
        Object value = recursiveSearch(null, json, name, null, null);
        return value;
    }

    public static ArrayList<Object> searchForObjects(JSONObject json, String name) {
        ArrayList<Object> myList = new ArrayList<Object>();
        recursiveSearch(null, json, name, null, myList);
        return myList;
    }

    public static Boolean searchForBoolean(JSONObject json, String name) {
        Boolean value = (Boolean) recursiveSearch(null, json, name, Boolean.class, null);
        return value;
    }

    public static ArrayList<Boolean> searchForBooleans(JSONObject json, String name) {
        ArrayList<Object> myList = new ArrayList<Object>();
        recursiveSearch(null, json, name, Boolean.class, myList);
        ArrayList<Boolean> newList = new ArrayList<Boolean>();
        for (int i = 0; i < myList.size(); i++) {
            newList.add((Boolean) myList.get(i));
        }
        return newList;
    }

    public static String searchForString(JSONObject json, String name) {
        String value = (String) recursiveSearch(null, json, name, String.class, null);
        return value;
    }

    public static ArrayList<String> searchForStrings(JSONObject json, String name) {
        ArrayList<Object> myList = new ArrayList<Object>();
        recursiveSearch(null, json, name, String.class, myList);
        ArrayList<String> newList = new ArrayList<String>();
        for (int i = 0; i < myList.size(); i++) {
            newList.add((String) myList.get(i));
        }
        return newList;
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
        Object value = recursiveSearch(null, json, name, Double.class, null);
        return convertToDouble(value);
    }

    public static ArrayList<Double> searchForDoubles(JSONObject json, String name) {
        ArrayList<Object> myList = new ArrayList<Object>();
        recursiveSearch(null, json, name, Double.class, myList);
        ArrayList<Double> newList = new ArrayList<Double>();
        for (int i = 0; i < myList.size(); i++) {
            newList.add(convertToDouble(myList.get(i)));
        }
        return newList;
    }

    private static Double convertToDouble(Object value) {
        if (value != null) {
            if (value.getClass() == Double.class) {
                return (Double) value;
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
        Object value = recursiveSearch(null, json, name, Long.class, null);
        if (value != null) {
            if (value.getClass() == Long.class) {
                return new Long((Long) value);
            } else if (value.getClass() == Integer.class) {
                return new Long(((Integer) value).longValue());
            }
        }

        return null;
    }

    public static ArrayList<Long> searchForLongs(JSONObject json, String name) {
        ArrayList<Object> myList = new ArrayList<Object>();
        recursiveSearch(null, json, name, Long.class, myList);
        ArrayList<Long> newList = new ArrayList<Long>();
        for (int i = 0; i < myList.size(); i++) {
            newList.add(convertToLong(myList.get(i)));
        }
        return newList;
    }

    private static Long convertToLong(Object value) {
        if (value != null) {
            if (value.getClass() == Long.class) {
                return (Long) value;
            } else if (value.getClass() == Integer.class) {
                return new Long(((Integer) value).longValue());
            }
        }
        return null;
    }


    /**
     * Search for an int value that has a particular name.
     *
     * @param json
     * @param name
     * @return
     */
    public static Integer searchForInteger(JSONObject json, String name) {
        Integer value = (Integer) recursiveSearch(null, json, name, Integer.class, null);
        return value;
    }

    public static ArrayList<Integer> searchForIntegers(JSONObject json, String name) {
        ArrayList<Object> myList = new ArrayList<Object>();
        recursiveSearch(null, json, name, Integer.class, myList);
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for (int i = 0; i < myList.size(); i++) {
            newList.add((Integer) myList.get(i));
        }
        return newList;
    }


    public static JSONObject searchForJSONObject(JSONObject json, String name) {
        JSONObject value = (JSONObject) recursiveSearch(null, json, name, JSONObject.class, null);
        return value;
    }

    public static ArrayList<JSONObject> searchForJSONObjects(JSONObject json, String name) {
        ArrayList<Object> myList = new ArrayList<Object>();
        recursiveSearch(null, json, name, JSONObject.class, myList);
        ArrayList<JSONObject> newList = new ArrayList<JSONObject>();
        for (int i = 0; i < myList.size(); i++) {
            newList.add((JSONObject) myList.get(i));
        }
        return newList;
    }


    /**
     * Search for a JSONArray that has a particular name.
     *
     * @param json
     * @param name
     * @return
     */
    public static JSONArray searchForJSONArray(JSONObject json, String name) {
        JSONArray value = (JSONArray) recursiveSearch(null, json, name, JSONArray.class, null);
        return value;
    }

    /**
     * Searches for all JSONArrays that have a particular name.
     *
     * @param json
     * @return
     */
    public static ArrayList<JSONArray> searchForJSONArrays(JSONObject json, String name) {
        ArrayList<Object> myList = new ArrayList<Object>();
        recursiveSearch(null, json, name, JSONArray.class, myList);
        ArrayList<JSONArray> newList = new ArrayList<JSONArray>();
        for (int i = 0; i < myList.size(); i++) {
            newList.add((JSONArray) myList.get(i));
        }
        return newList;
    }

    /**
     * Searches for all JSONArrays that do not have a name.
     *
     * @param json
     * @return
     */
    public static ArrayList<JSONArray> searchForJSONArrays(JSONObject json) {
        ArrayList<Object> myList = new ArrayList<Object>();
        recursiveSearch(null, json, null, JSONArray.class, myList);
        ArrayList<JSONArray> newList = new ArrayList<JSONArray>();
        for (int i = 0; i < myList.size(); i++) {
            newList.add((JSONArray) myList.get(i));
        }
        return newList;
    }

    /**
     * Search for all JSONArrays that have a particular set of values.
     *
     * @param json
     * @param values
     * @return
     */
    //TODO. This method has not been developed yet.
    public static JSONArray searchForJSONArray(JSONObject json, Object[] values) {

        return null;
    }


    //if myList is null, this means we are not trying to add items to a list.
    //if myList is not null, we shall add the items to myList.
    private static Object recursiveSearch(String objectToReduceName,
                                          Object objectToReduceValue,
                                          String requestedName,
                                          Class requestedObjectType,
                                          ArrayList<Object> myList
    ) {
        if (objectToReduceValue == null) {
            return null;
        } else if (objectToReduceValue.getClass() == JSONArray.class) {
            //Then we have a JSONArray. Is it one without a name?
            if (requestedName == null
                    && objectToReduceName == null) {
                JSONArray jsonArray = (JSONArray) objectToReduceValue;
                if (myList == null) {
                    return jsonArray;
                } else {
                    myList.add(jsonArray);
                }
            }
            //Then we have a JSONArray with a name. Is this what we wanted to return?
            if (requestedName != null
                    && requestedName.equals(objectToReduceName)
                    && (objectToReduceValue.getClass() == requestedObjectType
                    || requestedObjectType == null)) {
                //Then we have a JSONArray with the requested name and return type. Shall we return this or just add.
                if (myList == null) {
                    return objectToReduceValue;
                } else {
                    myList.add(objectToReduceValue);
                }
            }
            //Otherwise, Break down the JSONArray.
            JSONArray jsonArray = (JSONArray) objectToReduceValue;
            for (int i = 0; i < jsonArray.length(); i++) {
                Object tempObject = jsonArray.get(i);
                Object nullableValue = recursiveSearch(null, tempObject, requestedName, requestedObjectType, myList);
                if (nullableValue != null) {
                    return nullableValue;
                }
            }
        } else if (objectToReduceValue.getClass() == JSONObject.class) {
            //Then we have a JSONObject. Is this what we wanted to return?
            if (requestedName != null
                    && requestedName.equals(objectToReduceName)
                    && (objectToReduceValue.getClass() == requestedObjectType
                        || requestedObjectType == null)) {
                //Then we have a JSONObject with the requested name and return type. return this.
                if (myList == null) {
                    return objectToReduceValue;
                } else {
                    myList.add(objectToReduceValue);
                }
            }
            //Otherwise, Break down the JSONObject.
            JSONObject jsonObject = (JSONObject) objectToReduceValue;
            JSONArray names = jsonObject.names();
            JSONArray values = jsonObject.toJSONArray(names);
            for (int i = 0; i < names.length(); i++) {
                //Get whatever is inside the JSONObject, and call the search method again.
                String name = (String) names.get(i);
                Object value = values.get(i);
                Object nullableValue = recursiveSearch(name, value, requestedName, requestedObjectType, myList);
                if (nullableValue != null) {
                    return nullableValue;
                }
            }
        } else if (objectToReduceValue.getClass() == Double.class) {
            if (requestedObjectType == Double.class || requestedObjectType == null) {
                if (requestedName != null
                        && requestedName.equals(objectToReduceName)) {
                    //Check if objectToReduceValue is a valid double.
                    if (!Double.isNaN((Double) objectToReduceValue)) {
                        if (myList == null) {
                            return objectToReduceValue;
                        } else {
                            myList.add(objectToReduceValue);
                        }
                    }
                }
            }
        } else if (objectToReduceValue.getClass() == Long.class) {
            if (requestedObjectType == Long.class ||
                    requestedObjectType == Integer.class ||
                    requestedObjectType == null) {
                if (requestedName != null
                        && requestedName.equals(objectToReduceName)) {
                    if (myList == null) {
                        return objectToReduceValue;
                    } else {
                        myList.add(objectToReduceValue);
                    }
                }
            }
        } else if (objectToReduceValue.getClass() == Integer.class) {
            if (requestedObjectType == Integer.class ||
                    requestedObjectType == Double.class ||
                    requestedObjectType == Long.class ||
                    requestedObjectType == null) {
                if (requestedName != null
                        && requestedName.equals(objectToReduceName)) {
                    if (myList == null) {
                        return objectToReduceValue;
                    } else {
                        myList.add(objectToReduceValue);
                    }
                }
            }
        } else if (objectToReduceValue.getClass() == String.class) {
            if (requestedObjectType == String.class || requestedObjectType == null) {
                if (requestedName != null
                        && requestedName.equals(objectToReduceName)) {
                    if (myList == null) {
                        return objectToReduceValue;
                    } else {
                        myList.add(objectToReduceValue);
                    }
                }
            }
        } else if (objectToReduceValue.getClass() == Boolean.class) {
            if (requestedObjectType == Boolean.class || requestedObjectType == null) {
                if (requestedName != null
                        && requestedName.equals(objectToReduceName)) {
                    if (myList == null) {
                        return objectToReduceValue;
                    } else {
                        myList.add(objectToReduceValue);
                    }
                }
            }
        }

        //No results found. Return null.
        return null;
    }

}
