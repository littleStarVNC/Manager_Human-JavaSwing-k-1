package Image_NV;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import db.Home;

public class nv6 extends JFrame{
private JLabel LB;
Container con = new Container();
	public nv6() {
		

        URL imaurl = Home.class.getResource("nv6.jpg");
        if (imaurl != null) {

            ImageIcon icon = new ImageIcon(imaurl);
            Image ima = icon.getImage();
            Image token = ima.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            icon = new ImageIcon(token);

            URL urlvne = nv1.class.getResource("QL.jpg");
            Image img = Toolkit.getDefaultToolkit().createImage(urlvne);
            this.setIconImage(img);

            LB = new JLabel(icon);
            con.setLayout(new BorderLayout());

     

            LB.setLayout(new BorderLayout());
          

            con.add(LB);
            this.add(con, BorderLayout.CENTER);

            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setSize(200, 200);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            
        } else {
            System.err.println("NO Image");
        }
    }

	public static void main(String[] args) {
		new nv6();
	}
	}
	


