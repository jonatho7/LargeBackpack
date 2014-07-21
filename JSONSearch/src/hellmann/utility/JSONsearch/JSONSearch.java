package hellmann.utility.JSONsearch;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONSearch {

    public static Integer searchForInteger(Object obj, String requestedName) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == JSONArray.class) {
            //Then we have a JSONArray. Break down the JSONArray into JSONObjects.
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject tempObj = jsonArray.getJSONObject(i);
                Integer temp = searchForInteger(tempObj, requestedName);
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
                    //Then break down the array.
                    Integer temp = searchForInteger(tempObject, requestedName);
                    if (temp != null) {
                        return temp;
                    }
                } else if (tempObject.getClass() == JSONObject.class) {
                    //Then break down the object.
                    Integer temp = searchForInteger(tempObject, requestedName);
                    if (temp != null) {
                        return temp;
                    }
                } else {
                    //Then the Object is neither a JSONArray or JSONObject. This means we have a value.
                    if (requestedName.equals(names[i])) {
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

    //Works for Integer and String so far.
    //TODO. Make this work for searching for JSONObject and JSONArrays too would be pretty cool.
    public static Object searchForStringOrInteger(Object obj, String requestedName, Class classObject) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == JSONArray.class) {
            //Then we have a JSONArray. Break down the JSONArray into JSONObjects.
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject tempObj = jsonArray.getJSONObject(i);
                Object temp = searchForStringOrInteger(tempObj, requestedName, classObject);
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
                    //Then break down the array.
                    Object temp = searchForStringOrInteger(tempObject, requestedName, classObject);
                    if (temp != null) {
                        return temp;
                    }
                } else if (tempObject.getClass() == JSONObject.class) {
                    //Then break down the object.
                    Object temp = searchForStringOrInteger(tempObject, requestedName, classObject);
                    if (temp != null) {
                        return temp;
                    }
                } else {
                    //Then the Object is neither a JSONArray or JSONObject. This means we have a value.
                    //Searching for an Integer value. Try to search for a different type of value.
                    if (requestedName.equals(names[i])) {
                        //Then we have found the correct name: value pair. Get the value if the correct class type.
                        if (classObject == Integer.class) {
                            Double rotate = json.optDouble(names[i]);
                            if (!Double.isNaN(rotate)) {
                                //Then the value was valid. Return results.
                                return rotate.intValue();
                            }
                        } else if (classObject == String.class){
                            String myString = null;
                            try {
                                myString = json.getString(names[i]);
                                //TODO. Did not use optString because this returns an empty String.
                            } catch (Exception e) {
                                ;
                            }
                            if (myString != null){
                                return myString;
                            }

                        }

                    }

                }
            }
        }
        //No results found. Return null.
        return null;
    }
}