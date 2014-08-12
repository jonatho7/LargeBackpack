package testEnums;

public class DimensionsExractor {

    private static enum Dimension {
        WIDTH, HEIGHT
    }


    public static Integer getWidth(String dataSource) {
        return getDimension(dataSource, Dimension.WIDTH);
    }


    public static Integer getHeight(String dataSource) {
        return getDimension(dataSource, Dimension.HEIGHT);
    }


    private static Integer getDimension(String dataSource, DimensionsExractor.Dimension dimensionType) {
        if (dimensionType == null) {
            return null;
        }

        int exifDimensionTag;
        int jpegDimensionTag;
        if (dimensionType == Dimension.WIDTH) {
            exifDimensionTag = 400;
            jpegDimensionTag = 500;
        } else if (dimensionType == Dimension.HEIGHT) {
            exifDimensionTag = 600;
            jpegDimensionTag = 700;
        } else {
            throw new IllegalArgumentException("dimensionType was not equal to 'HEIGHT' or 'WIDTH'.");
        }

        int width = 50;

        return width;
    }


}
