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
    private ArrayList<Float> values = new ArrayList<>();
    
    public GlcmAttrs(float energy, float contrast, float homogenity, float IDM,
            float entropy, float mean) {
        values.add(energy);
        values.add(contrast);
        values.add(homogenity);
        values.add(IDM);
        values.add(entropy);
        values.add(mean);        
    }
    
    public GlcmAttrs() {
        values.add(0.f);
        values.add(0.f);
        values.add(0.f);
        values.add(0.f);
        values.add(0.f);
        values.add(0.f);
    }
    
    public float getEnergy() {
        return values.get(0);
    }

    public float getContrast() {
        return values.get(1);
    }

    public float getHomogenity() {
        return values.get(2);
    }

    public float getIDM() {
        return values.get(3);
    }

    public float getEntropy() {
        return values.get(4);
    }

    public float getMean() {
        return values.get(5);
    }
    
    public ArrayList<Float> getValuesList() {
        return values;
    }
    
    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        for(Float f : values) {
            stb.append(f);
            stb.append(" ");
        }
        return stb.toString();
    }
}
