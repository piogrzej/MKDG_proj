/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg.glcm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author z
 */
public class JLabelMouse extends JLabel implements MouseMotionListener {    

    private int mx = 0;
    private int my = 0;
    private int sx = 100;
    private int sy = 100;
    


    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        invalidate();
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.ORANGE);
        g.setColor(Color.red);
        g.drawRect(mx, my, sx, sy);// Recipe's Name        
        Graphics2D g2 = (Graphics2D) g;
    }
}