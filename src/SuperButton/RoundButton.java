package SuperButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class RoundButton extends JButton {
	
    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getSize().width , getSize().height , 15, 15);
        

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getSize().width , getSize().height , 15, 15);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 40); 
    }
    
}