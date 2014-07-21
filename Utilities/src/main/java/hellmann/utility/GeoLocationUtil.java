package hellmann.utility;

import org.json.JSONObject;
import org.securegraph.type.GeoPoint;

import java.util.ArrayList;

public class GeoLocationUtil {
    public static GeoPoint extractGeoLocationFromJSON(JSONObject json) {
        if (json == null) {
            System.out.println("Returning null");
            return null;
        }

        try {
            JSONObject formatObject = json.getJSONObject("format");
            JSONObject tagsObject = formatObject.getJSONObject("tags");
            String locationString = tagsObject.getString("location");
            GeoPoint geoPoint = parseGeoLocationString(locationString);
            return geoPoint;
        } catch (Exception e) {
            //LOGGER.info("Could not retrieve a \"geoLocation\" value from the JSON object.");
            System.out.println("Could not retrieve a \"geoLocation\" value from the JSON object.");
            e.printStackTrace();
        }

        return null;
    }

    private static GeoPoint parseGeoLocationStringUnsafe(String locationString) {
        int index = 0;
        int maxIndex = locationString.length() - 1;
        String latitudeSign = locationString.substring(0, 1);
        String latitudeString = locationString.substring(0, 8);
        Double latitude = Double.parseDouble(latitudeString);
        System.out.println(latitudeString);

        String longitudeString = locationString.substring(8, 17);
        Double longitude = Double.parseDouble(longitudeString);
        System.out.println(longitudeString);

        String altitudeString = locationString.substring(17, 25);
        Double altitude = Double.parseDouble(altitudeString);
        System.out.println(altitudeString);

        if (latitude != null && longitude != null && altitude != null) {
            return new GeoPoint(latitude, longitude, altitude);
        } else if (latitude != null && longitude != null && altitude == null) {
            return new GeoPoint(latitude, longitude);
        } else {
            return null;
        }
    }

    private static GeoPoint parseGeoLocationString(String locationString) {
        String myRegularExpression = "(\\+|\\-|/)";
        String[] tempValues = locationString.split(myRegularExpression);
        String[] values = removeNullsFromStringArray(tempValues);
        if (values.length < 2) {
            return null;
        }

        String latitudeValue = values[0];
        String latitudeSign = "";
        int indexOfLatitude = locationString.indexOf(latitudeValue);
        if (indexOfLatitude != 0) {
            latitudeSign = locationString.substring(0, 1);
        }
        String latitudeString = latitudeSign + latitudeValue;
        Double latitude = Double.parseDouble(latitudeString);

        String longitudeValue = values[1];
        String longitudeSign = "";
        int indexOfLongitude = locationString.indexOf(longitudeValue, indexOfLatitude + latitudeValue.length());
        String longitudePreviousChar = locationString.substring(indexOfLongitude - 1, indexOfLongitude);
        if (longitudePreviousChar.equals("-") || longitudePreviousChar.equals("+")) {
            longitudeSign = longitudePreviousChar;
        }
        String longitudeString = longitudeSign + longitudeValue;
        Double longitude = Double.parseDouble(longitudeString);

        String altitudeValue = null;
        Double altitude = null;
        if (values.length == 3) {
            altitudeValue = values[2];
            String altitudeSign = "";
            int indexOfAltitude = locationString.indexOf(altitudeValue, indexOfLongitude + longitudeValue.length());
            String altitudePreviousChar = locationString.substring(indexOfAltitude - 1, indexOfAltitude);
            if (altitudePreviousChar.equals("-") || altitudePreviousChar.equals("+")) {
                altitudeSign = altitudePreviousChar;
            }
            String altitudeString = altitudeSign + altitudeValue;
            altitude = Double.parseDouble(altitudeString);
        }

        if (latitude != null && longitude != null && altitude != null) {
            return new GeoPoint(latitude, longitude, altitude);
        } else if (latitude != null && longitude != null && altitude == null) {
            return new GeoPoint(latitude, longitude);
        } else {
            return null;
        }
    }

    public static void printStringArray(String[] array, String arrayName) {
        System.out.println(arrayName + ":");
        for (int i = 0; i < array.length; i++) {
            System.out.println(i + ": " + array[i]);
        }
    }

    public static String[] removeNullsFromStringArray(String[] array) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && !array[i].equals("")) {
                arrayList.add(array[i]);
            }
        }
        return StringUtils.createStringArrayFromList(arrayList);
    }

}
