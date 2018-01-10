/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg.glcm;

/**
 *
 * @author z
 */
public class GlcmAttrs {
    private float energy=0;
    private float contrast=0;
    private float homogenity=0;
    private float IDM=0;
    private float entropy=0;
    private float mean=0;
    
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
    
    @Override
    public String toString() {
        return energy + " " + contrast + " " + homogenity + " " + energy + " " + mean + " " + IDM;
    }
}
