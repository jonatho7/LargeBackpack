import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.drew.metadata.icc.IccDirectory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jon.hellmann on 6/17/14.
 */
public class TestTryCatchFinally {

    private String outputFilename = "metadata.csv";
    private String myDirectoryPath = "images2";
    private String supportedExtensions[] = {".jpg", ".jpeg", ".bmp", ".tiff", ".gif", ".psd", ".nef", ".cr2", ".orf", ".arw", ".rw2", ".crw"};
    private int numImagesProcessed = 0;
    private ArrayList<String> rejectedFiles = new ArrayList<String>();
    private FileWriter writer = null;

    public TestTryCatchFinally() {
        System.out.println("Starting program.");

        //Setup a FileWriter.
        try {
            writer = new FileWriter(outputFilename);

            //Iterate through all the images in a directory.
            File dir = new File(myDirectoryPath);
            File[] imageFiles = dir.listFiles();
            if (imageFiles != null) {
                for (File imageFile : imageFiles) {
                    if (isSupportedFileExtension(imageFile, supportedExtensions)) {
                        System.out.println("\n#######################################################");
                        System.out.println("Filename: " + imageFile.getName());
                        extractMetadata(imageFile);
                        numImagesProcessed++;
                    } else {
                        rejectedFiles.add(imageFile.getName());
                    }
                }
                writer.flush();
            } else {
                //TODO. Check on this.
                System.out.println("ERROR: myDirectoryPath was not a valid directory, " +
                        "or there were no images in the directory.");
                return;
            }
        } catch (IOException e) {
            System.err.println("Caught IOException:" + e.getMessage());
            System.err.println("Filename error, or writer.flush() error. Filename used: " + outputFilename);
        } finally {
            if (writer != null) {
                System.out.println("Closing FileWriter");
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("FileWriter was not opened. It will not be closed.");
            }
        }

        //Print out some of the processing data.
        System.out.println("\nNumber of images processed: " + numImagesProcessed);
        System.out.println("Files Rejected: " + rejectedFiles);
        System.out.println("Finished Successfully.");
    }

    /**
     * This method extracts the metadata from an image.
     * @param imageFile The imageFile to extract the metadata from.
     */
    public void extractMetadata(File imageFile)
    {
        try {
            //Extract all of the metadata.
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);

            //Print the metadata to System.out.
            for(Directory directory : metadata.getDirectories()){
                for(Tag tag : directory.getTags()) {
                    System.out.println(tag);
                }
            }

            //Write the metadata to a csv file.
            writer.append(imageFile.getPath() );
            writer.append("," + imageFile.getName() );

            //Get the Date/Time.
            Date date = getDate(metadata);
            writeString(writer, date.toString() );

            //Get the make and model of the device
            ExifIFD0Directory dir = metadata.getDirectory(ExifIFD0Directory.class);

            //Use this next line to return an Object, and then cast it to a String.
            //String make = (String) dir.getObject(ExifIFD0Directory.TAG_MAKE);
            String make = dir.getDescription(ExifIFD0Directory.TAG_MAKE);
            writeString(writer, make);

            String model = dir.getDescription(ExifIFD0Directory.TAG_MODEL);
            writeString(writer, model);

            //Move to the next line.
            writer.append("\n");

        } catch (ImageProcessingException e) {
            System.err.println("Caught ImageProcessingException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    /**
     * This method checks to see if the input file has a supported file extension.
     * The filename is converted to lowercase first, and then checked against the extensions.
     * @param file The file in which to check the extension.
     * @param extensions The String array of valid file extensions.
     * @return
     */
    public boolean isSupportedFileExtension(File file, String[] extensions)
    {
        for(String extension: extensions) {
            if (file.getName().toLowerCase().endsWith(extension)){
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks multiple directories to determine the date. It will return the date
     * once it receives the first Date/Time Original.
     * @param metadata
     * @return
     */
    public Date getDate(Metadata metadata)
    {
        Date date = null;

        ExifSubIFDDirectory exifDirectory = metadata.getDirectory(ExifSubIFDDirectory.class);
        date = exifDirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        if (date != null)
            return date;

        IccDirectory iccDirectory = metadata.getDirectory(IccDirectory.class);
        date = iccDirectory.getDate(IccDirectory.TAG_ICC_PROFILE_DATETIME);
        if (date != null)
            return date;

        ExifIFD0Directory exifIFD0dir = metadata.getDirectory(ExifIFD0Directory.class);
        date = exifIFD0dir.getDate(ExifIFD0Directory.TAG_DATETIME);
        if (date != null)
            return date;

        GpsDirectory gpsDirectory = metadata.getDirectory(GpsDirectory.class);
        date = gpsDirectory.getDate(GpsDirectory.TAG_GPS_DATE_STAMP);
        if (date != null)
            return date;

        //TODO.
        return date;
    }

    public void writeString(FileWriter writer, String string)
    {
        try {
            //The next line will write "null" if the string points to null.
            writer.append("," + string);
        } catch (IOException e) {
            System.err.println("Caught IOException during writer.append() call. String: " + string);
        }
    }

}
