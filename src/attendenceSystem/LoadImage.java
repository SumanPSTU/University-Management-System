package attendenceSystem;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LoadImage {

    private static final Logger logger = Logger.getLogger(LoadImage.class.getName());


    public static List<Mat> loadImagesFromFolder(String folderPath) {
        List<Mat> images = new ArrayList<>();
        List<File> files = getImageFilesFromFolder(folderPath);

        for (File file : files) {
            Mat image = Imgcodecs.imread(file.getAbsolutePath());
            if (image == null || image.empty()) {
                logger.warning("Failed to load image: " + file.getAbsolutePath());
                continue;
            }
            images.add(image);
        }

        return images;
    }

    // Get image files from a folder
    public static List<File> getImageFilesFromFolder(String folderPath) {
        List<File> files = new ArrayList<>();
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            logger.severe("Directory not found or is not accessible: " + folderPath);
            return files;
        }

        File[] imageFiles = folder.listFiles((dir, name) -> isImageFile(name));
        if (imageFiles != null) {
            for (File file : imageFiles) {
                files.add(file);
            }
        }
        return files;
    }

    // Helper method to check if a file is an image
    private static boolean isImageFile(String fileName) {
        String lowerCaseName = fileName.toLowerCase();
        return lowerCaseName.endsWith(".jpg") || lowerCaseName.endsWith(".png");
    }
}
