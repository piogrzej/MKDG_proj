/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg.glcm.Forms;

import mkdg.glcm.Classifier;
import helpers.Point;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
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
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author z
 */
public class MainForm extends javax.swing.JFrame implements MouseListener {

    private Mat imageMat;
    private BufferedImage img;
    private Classifier classifier = new Classifier();
    private DefaultListModel listModel = new DefaultListModel();
    private ArrayList<Point> pointsList = new ArrayList<>();
    private File imgFile = new File("img1.jpg");
    private Color[] colors = new Color[16];    
       
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        
        this.setTitle("MKDG - GLCM");
        
        initComponents();
        jLabel1.addMouseListener((MouseListener) this);
        jLabel1.addMouseMotionListener(jLabel1);
        jLabel1.setMarkerSize(Integer.parseInt(jTextField2.getText()), Integer.parseInt(jTextField3.getText()));
        
        grayLevelsComboBox.removeAllItems();
        grayLevelsComboBox.addItem("2");
        grayLevelsComboBox.addItem("4");
        grayLevelsComboBox.addItem("8");
        grayLevelsComboBox.addItem("16");
        grayLevelsComboBox.addItem("32");
        grayLevelsComboBox.addItem("64");
        grayLevelsComboBox.addItem("128");
        grayLevelsComboBox.addItem("256");
        
        grayLevelsComboBox.setSelectedIndex(4);
        
        MainForm mf = this;
        
        ActionListener cbActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mf.loadImage();
            }
        };
        
        grayLevelsComboBox.addActionListener(cbActionListener);
        
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
                
                if(jTextField2.getText() != null && !jTextField2.getText().isEmpty() && jTextField3.getText() != null && !jTextField3.getText().isEmpty())
                    jLabel1.setMarkerSize(Integer.parseInt(jTextField2.getText()), Integer.parseInt(jTextField3.getText()));
            }
        };
        jTextField3.getDocument().addDocumentListener(dcl);
        jTextField2.getDocument().addDocumentListener(dcl);        
        jList1.setModel(listModel);
        Point pt = new Point(1, 0); 
        AddPoint(pt);
        
        //colors for classifier
        colors[0] = new Color(255, 0, 0);
        colors[1] = new Color(0, 255, 0);
        colors[2] = new Color(0, 0, 255);
        colors[3] = new Color(255, 255, 0);
        colors[4] = new Color(255, 0, 255);
        colors[5] = new Color(0, 255, 255);
        colors[6] = new Color(255, 125, 0);
        colors[7] = new Color(255, 0, 125);        
        colors[8] = new Color(0, 255, 125);
        colors[9] = new Color(125, 255, 125);
        colors[10] = new Color(125, 0, 255);
        colors[11] = new Color(0, 125, 255);
        colors[12] = new Color(125, 125, 255);        
        colors[13] = new Color(255, 125, 125);
        colors[14] = new Color(255, 255, 255);
        colors[15] = new Color(0, 0, 0);     
        
        //Marker colors
        DocumentListener dcl2 = new DocumentListener() {
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
                
                if(jTextField1.getText() != null && !jTextField1.getText().isEmpty())
                    jLabel1.setMarkerColor(colors[Math.abs(jTextField1.getText().hashCode()%16)]);
            }
        };
        jTextField1.getDocument().addDocumentListener(dcl2);
    }
    
    private void AddPoint(Point point) {
        listModel.addElement(point);
        pointsList.add(point);
    }
    
    private void DeletePoint(int index) {
        listModel.remove(index);
        pointsList.remove(index);
    }
    
    private void loadImage() {                            
        imageMat = ImageLoader.LoadImage(this.imgFile,Integer.parseInt((String)grayLevelsComboBox.getSelectedItem()));
        img = ImageLoader.ToBufferedImage(imageMat);//ImageIO.read(file);
        jLabel1.setIcon(new ImageIcon(img.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_FAST)));
        classifier.Clear();

        jLabel1.setImageSize(img.getWidth(), img.getHeight());
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

        jLabel1 = new mkdg.glcm.JLabelMouse();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        checkBox1 = new java.awt.Checkbox();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        sizeLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        contrastLabel = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        energyLabel = new javax.swing.JLabel();
        entropyLabel = new javax.swing.JLabel();
        homogenityLabel = new javax.swing.JLabel();
        idmLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        meanLabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        grayLevelsComboBox = new javax.swing.JComboBox<>();
        label1 = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jTextFieldPointX = new javax.swing.JTextField();
        jTextFieldPointY = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        analyzeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cat Name");
        jLabel2.setToolTipText("");

        checkBox1.setLabel("Adding Known Set");

        jTextField2.setText("100");

        jTextField3.setText("100");

        sizeLabel.setText("jLabel3");

        jButton2.setText("Load Classifier");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        contrastLabel.setText("jLabel4");

        jButton3.setText("Save Classifier");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        energyLabel.setText("jLabel5");

        entropyLabel.setText("jLabel6");

        homogenityLabel.setText("jLabel7");

        idmLabel.setText("jLabel8");

        jButton1.setText("Load Image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        meanLabel.setText("jLabel9");

        jTextField1.setText("Category Name");

        jLabel3.setText("Marker Size X");
        jLabel3.setToolTipText("");

        jLabel4.setText("Marker Size Y");

        grayLevelsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setText("Gray Levels:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(checkBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addComponent(meanLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(idmLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homogenityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(entropyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(energyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contrastLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grayLevelsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(grayLevelsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
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
                .addComponent(meanLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sizeLabel.getAccessibleContext().setAccessibleName("");
        contrastLabel.getAccessibleContext().setAccessibleName("");
        energyLabel.getAccessibleContext().setAccessibleName("");
        entropyLabel.getAccessibleContext().setAccessibleName("");
        homogenityLabel.getAccessibleContext().setAccessibleName("");
        idmLabel.getAccessibleContext().setAccessibleName("");
        meanLabel.getAccessibleContext().setAccessibleName("");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jTextFieldPointX.setText("0");

        jTextFieldPointY.setText("1");

        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Del");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setText("X: ");

        jLabel6.setText("Y: ");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("GLCM Dirrection");

        jCheckBox2.setText("Print GLCM");

        analyzeBtn.setText("Analyze");
        analyzeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPointX, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPointY, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(analyzeBtn))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPointX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPointY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(41, 41, 41)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(analyzeBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String userDir = System.getProperty("user.home");        
        JFileChooser fc = new JFileChooser(userDir +"/Desktop");          
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.imgFile = fc.getSelectedFile();
            loadImage();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String userDir = System.getProperty("user.home");        
        JFileChooser fc = new JFileChooser(userDir +"/Desktop");          
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("csv", "txt", "csv"));
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                classifier = new Classifier(fc.getSelectedFile());
            } catch (IOException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String userDir = System.getProperty("user.home");        
        JFileChooser fc = new JFileChooser(userDir +"/Desktop");          
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("csv", "txt", "csv"));
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                classifier.Serialize(fc.getSelectedFile());
            } catch (IOException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(jTextFieldPointX.getText().length() > 0 && jTextFieldPointY.getText().length() > 0) {
            Point pt = new Point(Integer.parseInt(jTextFieldPointX.getText()),
                    Integer.parseInt(jTextFieldPointY.getText()));
            AddPoint(pt);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int index = jList1.getSelectedIndex();
        if(index < 0 ) return;
        DeletePoint(index);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void analyzeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzeBtnActionPerformed
        analyzeImage();
    }//GEN-LAST:event_analyzeBtnActionPerformed

    private void analyzeImage()
    {
        jLabel1.ClearRectangles();
        int xStep = imageMat.width()/50;
        int yStep = imageMat.height()/50;
        int cols = imageMat.width()/xStep;
        int rows = imageMat.height()/yStep;
        System.out.println("COLS ROWS: " + cols + " " + rows);
        GLCMparser glcm = new GLCMparser();
        
        glcm.setX_size(jLabel1.getSx());
        glcm.setY_size(jLabel1.getSy());
        
        glcm.setImg(imageMat);
        
        for(int x=0; x<cols; ++x)
        {
            for(int y=0; y<rows; ++y)
            {
                int posX = x*xStep;//*imageMat.width()/jLabel1.getWidth();
                int posY = y*yStep;//*imageMat.height()/jLabel1.getHeight();
                //System.out.println(posX + " " + posY + "  " + x*xStep + " " + y*yStep);
                
                glcm.setX_poss(posX);
                glcm.setY_poss(posY);
                glcm.pars(pointsList);
                GlcmAttrs attrs = new GlcmAttrs(glcm.getEnergy(), glcm.getContrast(), glcm.getHomogenity(), glcm.getIDM(), glcm.getEntropy(), glcm.getMean());
                String c = classifier.Classify(attrs);
                if(!c.isEmpty())
                {
                    System.out.println(c);                    
                    Rectangle rect = new Rectangle(x*xStep, y*yStep, jLabel1.getSx(), jLabel1.getSy());
                    jLabel1.AddRectangle(rect, colors[Math.abs(jTextField1.getText().hashCode()%16)]);
                }
                //draw missed black thingies
                /*else{
                    Rectangle rect = new Rectangle(x*xStep, y*yStep, jLabel1.getSx(), jLabel1.getSy());
                    jLabel1.AddRectangle(rect, Color.BLACK);   
                }/**/
                    
            }
        }
        
    }
    
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
                mf.loadImage();
                mf.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyzeBtn;
    private java.awt.Checkbox checkBox1;
    private javax.swing.JLabel contrastLabel;
    private javax.swing.JLabel energyLabel;
    private javax.swing.JLabel entropyLabel;
    private javax.swing.JComboBox<String> grayLevelsComboBox;
    private javax.swing.JLabel homogenityLabel;
    private javax.swing.JLabel idmLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox2;
    private mkdg.glcm.JLabelMouse jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextFieldPointX;
    private javax.swing.JTextField jTextFieldPointY;
    private java.awt.Label label1;
    private javax.swing.JLabel meanLabel;
    private javax.swing.JLabel sizeLabel;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void mouseClicked(MouseEvent e) {
       System.out.println(e.getX() + " " + e.getY() + " " + e.getX()*imageMat.width()/jLabel1.getWidth() + " " + e.getY()*imageMat.height()/jLabel1.getHeight());
       GLCMparser glcm = new GLCMparser();
       glcm.setX_poss(e.getX()*imageMat.width()/jLabel1.getWidth());
       glcm.setY_poss(e.getY()*imageMat.height()/jLabel1.getHeight());
       glcm.setX_size(jLabel1.getSx());
       glcm.setY_size(jLabel1.getSy());
       glcm.setImg(imageMat);
                     
       if(this.jCheckBox2.isSelected())
           glcm.pars(pointsList, true);
       else
           glcm.pars(pointsList);
       
       GlcmAttrs attrs = new GlcmAttrs(glcm.getEnergy(), glcm.getContrast(), glcm.getHomogenity(), glcm.getIDM(), glcm.getEntropy(), glcm.getMean());
       
       this.contrastLabel.setText(attrs.getContrastAsString());
       this.energyLabel.setText(attrs.getEnergyAsString());
       this.entropyLabel.setText(attrs.getEntropyAsString());
       this.homogenityLabel.setText(attrs.getHomogenityAsString());
       this.idmLabel.setText(attrs.getIDMAsString());
       this.meanLabel.setText(attrs.getMeanAsString());
              
       if(checkBox1.getState())
           classifier.AddKnownObject(attrs, jTextField1.getText());
       else
           System.out.println(classifier.Classify(attrs));
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
}