package Image_NV;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import db.Home;

public class nv1 extends JFrame{
private JLabel LB;
private JPanel pan ;
	public nv1() {
		
	     URL urlvne = nv1.class.getResource("QL.jpg");
         Image img = Toolkit.getDefaultToolkit().createImage(urlvne);
         this.setIconImage(img);
         
         
        URL imaurl = Home.class.getResource("CEO.jpg");
        if (imaurl != null) {

            ImageIcon icon = new ImageIcon(imaurl);
LB = new JLabel();

     addComponentListener(new ComponentAdapter() {

		@Override
		public void componentResized(ComponentEvent e) {
			 Image ima = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			 ImageIcon imaicon = new ImageIcon(ima);
			 LB.setIcon(imaicon);
		
		}
    	 
	});
           pan = new JPanel();
           pan.setLayout(new BorderLayout());
            LB.setLayout(new BorderLayout());
          

            pan.add(LB);
            this.add(pan, BorderLayout.CENTER);

            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setSize(200, 200);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            
        } else {
            System.err.println("NO Image");
        }
    }

	public static void main(String[] args) {
		new nv1();
	}
	}
	

