/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg.glcm;

import java.util.ArrayList;

/**
 *
 * @author z
 */
public class GlcmAttrs {
    
    private ArrayList<Double> values = new ArrayList<>();
    
    public GlcmAttrs(double energy, double contrast, double homogenity, double IDM,
            double entropy, double mean) {
        values.add(energy);
        values.add(contrast);
        values.add(homogenity);
        values.add(IDM);
        values.add(entropy);
        values.add(mean);        
    }
    
    public GlcmAttrs() {
        values.add(0.0);
        values.add(0.0);
        values.add(0.0);
        values.add(0.0);
        values.add(0.0);
        values.add(0.0);
    }
    
    public double getEnergy() {
        return values.get(0);
    }

    public double getContrast() {
        return values.get(1);
    }

    public double getHomogenity() {
        return values.get(2);
    }

    public double getIDM() {
        return values.get(3);
    }

    public double getEntropy() {
        return values.get(4);
    }

    public double getMean() {
        return values.get(5);
    }
    
    public String getEnergyAsString() {
        return "Energy: "+values.get(0);
    }

    public String getContrastAsString() {
        return "Contrast: "+values.get(1);
    }

    public String getHomogenityAsString() {
        return "Homogenity: "+values.get(2);
    }

    public String getIDMAsString() {
        return "IDM: "+values.get(3);
    }

    public String getEntropyAsString() {
        return "Entropy: "+values.get(4);
    }

    public String getMeanAsString() {
        return "Mean: "+values.get(5);
    }
    
    public ArrayList<Double> getValuesList() {
        return values;
    }
    
    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        values.stream().map((f) -> {
            stb.append(f);
            return f;
        }).forEachOrdered((_item) -> {
            stb.append(" ");
        });
        return stb.toString();
    }
}
