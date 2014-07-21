package hellmann.utility;

import java.util.ArrayList;
import java.util.Arrays;

public class StringUtilsUsage {
    public static void main(String[] args) {
        ArrayList<String> ffmpegOptions = new ArrayList<String>();
        ffmpegOptions.add("-y");
        ffmpegOptions.add("-i");
        ffmpegOptions.add("path goes here");
        ffmpegOptions.add("-vcodec");
        ffmpegOptions.add("libx264");
        ffmpegOptions.add("-vprofile");
        ffmpegOptions.add("high");

        String[] mine = StringUtils.createStringArrayFromList(ffmpegOptions);
        System.out.println(Arrays.toString(mine));
    }

}
