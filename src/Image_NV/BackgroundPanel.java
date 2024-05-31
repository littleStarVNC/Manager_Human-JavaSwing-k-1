package Image_NV;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Gate.Signin;

public  class BackgroundPanel extends JPanel {
        private Image background;

        public BackgroundPanel() {
            URL imageUrl = Signin.class.getResource("QL.jpg");
            if (imageUrl != null) {
                background = new ImageIcon(imageUrl).getImage();
            }
        }

        @Override   
        protected void paintComponent(Graphics f) {
            super.paintComponent(f);
            if (background != null) {
                f.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
         
        }
    }

