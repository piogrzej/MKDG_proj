/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg.glcm.Forms;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import mkdg.glcm.GLCMparser;
import mkdg.glcm.GlcmAttrs;
import mkdg.glcm.ImageLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 *
 * @author z
 */
public class MainForm extends javax.swing.JFrame implements MouseListener {

    private Mat imageMat;
    private BufferedImage img;
    
    Map<String, List<GlcmAttrs>> knownClasses = new HashMap<>();
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        jLabel1.addMouseListener((MouseListener) this);
        jLabel1.addMouseMotionListener(jLabel1);
        jLabel1.setMarkerSize(Integer.parseInt(jTextField2.getText()), Integer.parseInt(jTextField3.getText()));
        DocumentListener dcl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ParseInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ParseInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                ParseInput();
            }
            
            private void ParseInput() {
                jLabel1.setMarkerSize(Integer.parseInt(jTextField2.getText()), Integer.parseInt(jTextField3.getText()));
            }
        };
        jTextField3.getDocument().addDocumentListener(dcl);
        jTextField2.getDocument().addDocumentListener(dcl);
    }
    
    private void loadImage(File file) {                            
        imageMat = ImageLoader.LoadImage(file);
        img = ImageLoader.ToBufferedImage(imageMat);//ImageIO.read(file);
        jLabel1.setIcon(new ImageIcon(img.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_FAST)));
        knownClasses.clear();

        jLabel1.setImageSize(img.getWidth(), img.getHeight());
        //sizeLabel.setText("Image size: "+img.getWidth() + "x" + img.getHeight());
        sizeLabel.setText("Image size: "+img.getWidth() + "x" + img.getHeight());
        
        this.contrastLabel.setText("");
        this.energyLabel.setText("");
        this.entropyLabel.setText("");
        this.homogenityLabel.setText("");
        this.idmLabel.setText("");
        this.meanLabel.setText("");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        checkbox1 = new java.awt.Checkbox();
        jLabel1 = new mkdg.glcm.JLabelMouse();
        sizeLabel = new javax.swing.JLabel();
        contrastLabel = new javax.swing.JLabel();
        energyLabel = new javax.swing.JLabel();
        entropyLabel = new javax.swing.JLabel();
        homogenityLabel = new javax.swing.JLabel();
        idmLabel = new javax.swing.JLabel();
        meanLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Load Image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText("Category Name");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cat Name");
        jLabel2.setToolTipText("");

        checkbox1.setLabel("Adding Known Set");

        jLabel1.setText("jLabel1");

        sizeLabel.setText("jLabel3");

        contrastLabel.setText("jLabel4");

        energyLabel.setText("jLabel5");

        entropyLabel.setText("jLabel6");

        homogenityLabel.setText("jLabel7");

        idmLabel.setText("jLabel8");

        meanLabel.setText("jLabel9");

        jLabel3.setText("Marker Size X");
        jLabel3.setToolTipText("");

        jLabel4.setText("Marker Size Y");

        jTextField2.setText("100");

        jTextField3.setText("100");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(checkbox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(meanLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(idmLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homogenityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(entropyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(energyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contrastLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contrastLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(energyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entropyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(homogenityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(meanLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        sizeLabel.getAccessibleContext().setAccessibleName("");
        contrastLabel.getAccessibleContext().setAccessibleName("");
        energyLabel.getAccessibleContext().setAccessibleName("");
        entropyLabel.getAccessibleContext().setAccessibleName("");
        homogenityLabel.getAccessibleContext().setAccessibleName("");
        idmLabel.getAccessibleContext().setAccessibleName("");
        meanLabel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String userDir = System.getProperty("user.home");        
        JFileChooser fc = new JFileChooser(userDir +"/Desktop");          
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            loadImage(fc.getSelectedFile());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    static {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainForm mf = new MainForm();
                mf.loadImage(new File("img1.jpg"));
                mf.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Checkbox checkbox1;
    private javax.swing.JLabel contrastLabel;
    private javax.swing.JLabel energyLabel;
    private javax.swing.JLabel entropyLabel;
    private javax.swing.JLabel homogenityLabel;
    private javax.swing.JLabel idmLabel;
    private javax.swing.JButton jButton1;
    private mkdg.glcm.JLabelMouse jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel meanLabel;
    private javax.swing.JLabel sizeLabel;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void mouseClicked(MouseEvent e) {
       System.out.println(e.getX() + " " + e.getY() + " " + e.getX()*imageMat.width()/jLabel1.getWidth() + " " + e.getY()*imageMat.height()/jLabel1.getHeight());
       GLCMparser glcm = new GLCMparser();
       glcm.setX_poss(e.getX()*imageMat.width()/jLabel1.getWidth());
       glcm.setY_poss(e.getY()*imageMat.height()/jLabel1.getHeight());
       glcm.setX_size(Integer.parseInt(jTextField2.getText()));
       glcm.setY_size(Integer.parseInt(jTextField3.getText()));
       glcm.setImg(imageMat);
       glcm.pars();
       GlcmAttrs attrs = new GlcmAttrs(glcm.getEnergy(), glcm.getContrast(), glcm.getHomogenity(), glcm.getIDM(), glcm.getEntropy(), glcm.getMean());
       
       this.contrastLabel.setText(attrs.getContrastAsString());
       this.energyLabel.setText(attrs.getEnergyAsString());
       this.entropyLabel.setText(attrs.getEntropyAsString());
       this.homogenityLabel.setText(attrs.getHomogenityAsString());
       this.idmLabel.setText(attrs.getIDMAsString());
       this.meanLabel.setText(attrs.getMeanAsString());
              
       if(checkbox1.getState())
           AddKnownObject(attrs);
       else
           Classify(attrs);
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    private void AddKnownObject(GlcmAttrs attrs) {
        String key = jTextField1.getText();
        knownClasses.putIfAbsent(key, new ArrayList<>());
        knownClasses.get(key).add(attrs);
    }

    private void Classify(GlcmAttrs attrs) {
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
        System.out.println(minClassName);
    }
    
    private double Distance(GlcmAttrs attrs1, GlcmAttrs attrs2) {
        ArrayList<Double> attrsA = attrs1.getValuesList();
        ArrayList<Double> attrsB = attrs2.getValuesList();
        double distance = 0;
        for(int i=0; i<attrsA.size(); i++) {
            double a = attrsA.get(i);
            double b = attrsB.get(i);
            distance += b*b - a*a;
        }
        return Math.sqrt(distance);
    }
}
