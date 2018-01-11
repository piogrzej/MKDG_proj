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
    private double contrast=0;
    private double energy=0;
    private double entropy=0;
    private double homogenity=0;
    private double IDM=0;//inverse diffrence moment
    private double mean=0;
    
    private void resetValues()
    {
        this.energy = 0;
        this.contrast = 0;
        this.homogenity = 0;
        this.IDM = 0;
        this.entropy = 0;
        this.mean = 0;
    }
    
    private int getStartX()
    {
        int x = (x_poss - (x_size/2));
        x = (x>0) ? x : 0;
        
        return x;
    }
    
    private int getStartY()
    {
        int y = (y_poss - (y_size/2));
        y = (y>0) ? y : 0;
        
        return y;
    }
    
    private int getRowCount()
    {
        int startY = getStartY();
        int rowsCount = img.rows();
        int y = (y_poss + (y_size/2));
        int rows = (y<rowsCount) ? (y-startY) : (rowsCount-startY);
        
        return rows;
    }
    
    private int getColumnCount()
    {
        int startX = getStartX();
        int colsCount = img.cols();
        int x = (x_poss + (x_size/2));
        int cols = (x<colsCount) ? (x-startX) : (colsCount-startX);
        
        return cols;
    }
    
    public void pars()
    {
        this.resetValues();
        
        Mat gl = Mat.zeros(GRAY_LEVELS, GRAY_LEVELS, CvType.CV_64F);
        Mat glt = gl.clone();
        
        int rows = getRowCount(), cols = getColumnCount();
        int startX = getStartX(), startY = getStartY();
        
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols-1; x++) {

                int i = (int) img.get(startY + y, startX + x)[0];
                int j = (int) img.get(startY + y, startX + x + 1)[0];

                double[] count = gl.get(i, j);
                count[0]++;
                gl.put(i, j, count);
            }
        }
        
        Core.transpose(gl, glt);
        Core.add(gl, glt, gl);
        Scalar sum = Core.sumElems(gl);
        Core.divide(gl, sum, gl);
        
        for(int y=0;y<GRAY_LEVELS;y++)
            for(int x=0;x<GRAY_LEVELS;x++)
            {
                
                double value = gl.get(y, x)[0];
                
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

    public double getEnergy() {
        return energy;
    }

    public double getContrast() {
        return contrast;
    }

    public double getHomogenity() {
        return homogenity;
    }

    public double getIDM() {
        return IDM;
    }

    public double getEntropy() {
        return entropy;
    }

    public double getMean() {
        return mean;
    }
    
    public GLCMparser()
    {
        
    }
    
}
