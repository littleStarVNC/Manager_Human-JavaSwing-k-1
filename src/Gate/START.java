package Gate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class START extends JFrame {
    public JPanel pn;
    public JButton btStart;

    public START() {
        setTitle("YOU ARE READY ?");

        URL url = START.class.getResource("QL.jpg");
        Image img = Toolkit.getDefaultToolkit().createImage(url);
        this.setIconImage(img);

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      
        pn = new JPanel();
       this.add(pn);
     
     

        btStart = new JButton("START");

        pn.setLayout(new FlowLayout(FlowLayout.CENTER));
       
        pn.setBackground(new Color(255, 255, 255));
        
        btStart.setBackground(new Color(0, 128, 255));
        pn.add(btStart);
        
    
        btStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Signin sn = new Signin();
                sn.setTitle("LOGIN");
                sn.setVisible(true);
                sn.setLocationRelativeTo(null);
            }
        });
    }

    public static void main(String[] args) {
    	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    START st = new START();
                    st.setVisible(true);
                    st.setExtendedState(JFrame.MAXIMIZED_BOTH);

                    st.setLocationRelativeTo(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


