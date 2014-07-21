package hellmann.utility;

import java.util.List;

public class StringUtils {
    static String[] createStringArrayFromList(List<String> list) {
        String[] stringArray = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            stringArray[i] = list.get(i);
        }
        return stringArray;
    }
}
