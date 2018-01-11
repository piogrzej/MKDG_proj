/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg.glcm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;

/**
 *
 * @author z
 */
public class JLabelMouse extends JLabel implements MouseMotionListener {    

    private int mx = 0;
    private int my = 0;
    private int sx = 100;
    private int sy = 100;
    private int imageSizeX = 1;
    private int imageSizeY = 1;    
    private float ratiox = 1.f;
    private float ratioy = 1.f;
    
    public void setMarkerSize(int x, int y) {
        sx = x;
        sy = y;
    }
    
    public void setImageSize(int x, int y) {
        imageSizeX = x;
        imageSizeY = y;
        ratiox = (float)imageSizeX/this.getWidth();
        ratioy = (float)imageSizeY/this.getHeight();
    }
    


    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.ORANGE);
        g.setColor(Color.red);
        
        
        g.drawRect((int) ((mx*ratiox - sx/2)/ratiox), (int)((my*ratioy - sy/2)/ratioy), (int)(sx/ratiox), (int)(sy/ratioy));
        Graphics2D g2 = (Graphics2D) g;
    }
}
