package mkdg.glcm.Forms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mkdg.glcm.GlcmAttrs;

/**
 *
 * @author z
 */
public final class Classifier {
    private Map<String, List<GlcmAttrs>> knownClasses = new HashMap<>();
    
    public Classifier() {
        
    }
    
    public Classifier(File file) throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(";");
                if(split.length != 7) throw new RuntimeException("KNN - Expected 7 columns found: " + split.length);
                GlcmAttrs attrs = new GlcmAttrs(Double.parseDouble(split[1]), 
                        Double.parseDouble(split[2]), 
                        Double.parseDouble(split[3]), 
                        Double.parseDouble(split[4]), 
                        Double.parseDouble(split[5]), 
                        Double.parseDouble(split[6]));
                AddKnownObject(attrs, split[0]);
            }
        }
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
    
    public void Serialize(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for (Map.Entry<String, List<GlcmAttrs>> entry : knownClasses.entrySet()) {
                String className = entry.getKey();
                List<GlcmAttrs> value = entry.getValue();
                for(GlcmAttrs classAttr : value) {
                    printWriter.println(className + classAttr.toString().replaceAll("\\s+",";"));
                }            
            }
            printWriter.flush();
        }
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
