package mkdg.glcm;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.io.File;

public class Main {
    static {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {

        File file = new File("img1.jpg");
        Mat mt = ImageLoader.LoadImage(file);
        return;
    }

}
