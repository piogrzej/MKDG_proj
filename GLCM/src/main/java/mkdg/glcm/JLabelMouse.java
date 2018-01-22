/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg.glcm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
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
    
    private ArrayList<Rectangle> areas = new ArrayList<>();
    private ArrayList<Color> rectangles = new ArrayList<>();
    
    public void AddRectangle(Rectangle rect, Color col) {
        areas.add(rect);
        rectangles.add(col);
    }
    
    public void ClearRectangles(){
        areas.clear();
        rectangles.clear();
    }
    
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
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.ORANGE);
        g.setColor(Color.red);                
        g.drawRect((int) ((mx*ratiox - sx/2)/ratiox), (int)((my*ratioy - sy/2)/ratioy), (int)(sx/ratiox), (int)(sy/ratioy));
        
        for(int i=0; i<areas.size(); i++) {
            Rectangle r = areas.get(i);
            Color c = rectangles.get(i);
            g2.setColor(c);
            //g2.setStroke(new BasicStroke(2));
            g2.drawRect((int)(r.x/ratiox), (int)(r.y/ratioy), (int)(r.width/ratiox), (int)(r.height/ratioy));
        }
                
    }

    public int getSx() {
        return sx;
    }

    public int getSy() {
        return sy;
    }
    
    
}
