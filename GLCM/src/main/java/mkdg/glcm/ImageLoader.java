package mkdg.glcm;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import org.opencv.core.Core;
import org.opencv.core.Scalar;

public class ImageLoader {
    
    public static Mat LoadImage(File file,int levels) {    
        Mat gl = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        //changing of grayscale
        if(256!=levels)
        {
            Scalar div = new Scalar(256/levels);
            Scalar two = new Scalar(0.5);
            Core.divide(gl, div, gl);
            Core.multiply(gl, div, gl);
            div.mul(two);
            Core.add(gl, div, gl);
        }
        
        return gl;
    }

    public static BufferedImage ToBufferedImage(Mat m){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }
}
