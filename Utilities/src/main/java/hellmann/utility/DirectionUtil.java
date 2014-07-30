package hellmann.utility;

public class DirectionUtil {

    private static String convertDegreeToDirection(double degree) {
        if (degree > 22.5 && degree <= 67.5) {
            return "NE";
        } else if (degree > 67.5 && degree <= 112.5) {
            return "E";
        } else if (degree > 112.5 && degree <= 157.5) {
            return "SE";
        } else if (degree > 157.5 && degree <= 202.5) {
            return "S";
        } else if (degree > 202.5 && degree <= 247.5) {
            return "SW";
        } else if (degree > 247.5 && degree <= 292.5) {
            return "W";
        } else if (degree > 292.5 && degree <= 337.5) {
            return "NW";
        } else if ((degree > 337.5 && degree <= 360)
                || (degree >= 0 && degree <= 22.5)) {
            return "N";
        } else {
            //because improper degree. Negative degree.
            return null;
        }
    }
}
