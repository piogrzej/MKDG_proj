package mkdg.glcm;

import helpers.Point;
import org.opencv.core.Core;
import org.opencv.core.Mat;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        //wywołanie: program /sciezka/do/pliku/jpg pozycjaX pozycjaY rozmiarX rozmiarY liczbaKierunkow kierunek0x kierunek0y ...
        String pathToImg = args[0];
        int x_poss = Integer.parseInt(args[1]);
        int y_poss = Integer.parseInt(args[2]);
        int x_size = Integer.parseInt(args[3]);
        int y_size = Integer.parseInt(args[4]);
        int directionsCount = Integer.parseInt(args[5]);
        
        File file = new File(pathToImg);
        Mat mt = ImageLoader.LoadImage(file);
        
        GLCMparser parser = new GLCMparser();
        
        parser.setImg(mt);
        
        parser.setX_poss(x_poss);
        parser.setY_poss(y_poss);
        
        parser.setX_size(x_size);
        parser.setY_size(y_size);
        
        List<Point> directions = new ArrayList();
        
        for(int i=0; i<directionsCount; ++i)
        {
            directions.add(new Point(Integer.parseInt(args[5+2*i+1]),Integer.parseInt(args[5+2*i+2])));
        }
       
        parser.pars(directions);
        
        System.out.println(parser.getContrast() + " " + 
                parser.getEnergy() + " " + 
                parser.getEntropy() + " " + 
                parser.getHomogenity() + " " + 
                parser.getIDM() + " " + 
                parser.getMean() 
        );
        
    }
}
