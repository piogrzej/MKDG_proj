/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg.glcm;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

/**
 *
 * @author piotr
 */
public class GLCMparser {
    
    private static final int GRAY_LEVELS = 256;
    
    private Mat img;//obrazek
    
    private int x_size;//rozmiar obszaru w osi X
    private int y_size;//rozmiar obszaru w osi Y
    
    //umieszenie obszaru na obrazku
    private int x_poss;
    private int y_poss;
    
    //wyliczone wspolczynniki
    private float energy=0;
    private float contrast=0;
    private float homogenity=0;
    private float IDM=0;
    private float entropy=0;
    private float mean=0;
    
    private void resetValues()
    {
        this.energy = 0.0f;
        this.contrast = 0.0f;
        this.homogenity = 0.0f;
        this.IDM = 0.0f;
        this.energy = 0.0f;
        this.mean = 0.0f;
    }
    
    public void pars()
    {
        this.resetValues();
        
        Mat gl = Mat.zeros(GRAY_LEVELS, GRAY_LEVELS, CvType.CV_64F);
        Mat glt = gl.clone();
        
        int rows = y_size, cols = x_size;
        
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols-1; x++) {

                int i = (int) img.get(y_poss + y, x_poss + x)[0];
                int j = (int) img.get(y_poss + y, x_poss + x + 1)[0];

                double[] count = gl.get(i, j);
                count[0]++;
                gl.put(i, j, count);
            }
        }
        
        Core.transpose(gl, glt);
        Core.add(gl, glt, gl);
        Scalar sum = Core.sumElems(gl);
        Core.divide(gl, sum, gl);
        
        for(int y=0;y<y_size;y++)
            for(int x=0;x<x_size;x++)
            {
                float value = (float)gl.get(y, x)[0];
                
                energy += value*value;
                contrast += (x-y)*(x-y)*value;
                homogenity += value/(1+Math.abs(y-x));
                IDM += (y!=x) ? ( value / ((x-y)*(x-y)) ) : 0;
                entropy -= ( value != 0.0f ) ? value * Math.log10(value) : 0;
                mean += 0.5*(y*value+x*value);
            }
        
    }
    
    public void setImg(Mat img) {
        this.img = img;
    }

    public void setX_size(int x_size) {
        this.x_size = x_size;
    }

    public void setY_size(int y_size) {
        this.y_size = y_size;
    }

    public void setX_poss(int x) {
        this.x_poss = x;
    }

    public void setY_poss(int y) {
        this.y_poss = y;
    }

    public float getEnergy() {
        return energy;
    }

    public float getContrast() {
        return contrast;
    }

    public float getHomogenity() {
        return homogenity;
    }

    public float getIDM() {
        return IDM;
    }

    public float getEntropy() {
        return entropy;
    }

    public float getMean() {
        return mean;
    }
    
    public GLCMparser()
    {
        
    }
    
}
