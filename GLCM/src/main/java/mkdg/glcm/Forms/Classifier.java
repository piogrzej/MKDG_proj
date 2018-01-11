package mkdg.glcm.Forms;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mkdg.glcm.GlcmAttrs;

/**
 *
 * @author z
 */
public class Classifier {
    private Map<String, List<GlcmAttrs>> knownClasses = new HashMap<>();
    
    public Classifier() {
        
    }
    
    public Classifier(File file) {
        throw new RuntimeException("Not implemented");
    }
        
    public void AddKnownObject(GlcmAttrs attrs, String className) {
        String key = className;
        knownClasses.putIfAbsent(key, new ArrayList<>());
        knownClasses.get(key).add(attrs);
    }
    
    public void Clear() {
        knownClasses.clear();
    }
        
    public String Classify(GlcmAttrs attrs) {
        String minClassName = "";
        double minDistance = Double.MAX_VALUE;
        for (Map.Entry<String, List<GlcmAttrs>> entry : knownClasses.entrySet()) {
            String className = entry.getKey();
            List<GlcmAttrs> value = entry.getValue();
            for(GlcmAttrs classAttr : value) {
                double distance = Distance(attrs, classAttr);
                if(distance < minDistance) {
                    minClassName = className;
                    minDistance = distance;
                }
            }            
        }
        return minClassName;
    }
    
    public void Serialize(File file) {
        throw new RuntimeException("Not implemented");
   }
    
    private double Distance(GlcmAttrs attrs1, GlcmAttrs attrs2) {
        ArrayList<Double> attrsA = attrs1.getValuesList();
        ArrayList<Double> attrsB = attrs2.getValuesList();
        double distance = 0;
        for(int i=0; i<attrsA.size(); i++) {
            double a = attrsA.get(i);
            double b = attrsB.get(i);
            distance += (b-a)*(b-a);
        }
        return Math.sqrt(distance);
    }
}
