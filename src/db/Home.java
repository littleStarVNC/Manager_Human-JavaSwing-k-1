package db;

import java.awt.BorderLayout;
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
import javax.swing.UIManager;

public class Home extends JFrame {
    private JLabel lb1;

    public Home() {
        JPanel panel = new JPanel();

        
        URL imgeframe=Home.class.getResource("QL.jpg");
        Image ige = Toolkit.getDefaultToolkit().createImage(imgeframe);
        this.setIconImage(ige);
        
        URL imageUrl = Home.class.getResource("nhansu.jpg");
        if (imageUrl != null) {
            ImageIcon imaICon1 = new ImageIcon(imageUrl);

            JMenuBar menuBar = new JMenuBar();

            JMenu viewMenu = new JMenu("View");
            JMenuItem manageItem = new JMenuItem("MANAGE");
            JMenuItem exitItem = new JMenuItem("Exit");

            viewMenu.add(manageItem);
            viewMenu.addSeparator();
            viewMenu.add(exitItem);
            menuBar.add(viewMenu);

            setJMenuBar(menuBar);

            lb1 = new JLabel();
            panel.setLayout(new BorderLayout());
            panel.add(lb1, BorderLayout.CENTER);
            add(panel, BorderLayout.CENTER);
            
            setTitle("HOME");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setExtendedState(MAXIMIZED_BOTH);
            setLocationRelativeTo(null);
            setVisible(true);
            
            
addComponentListener(new ComponentAdapter() {

	@Override
	public void componentResized(ComponentEvent e) {
		Image image = imaICon1.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imicon2 = new ImageIcon(image);
		
		lb1.setIcon(imicon2);
	}
	
});

            manageItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    QuanLyNhanSu ql = new QuanLyNhanSu("MANAGE");
                    ql.setTitle("MANAGE");
                }
            });

            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        } else {
            System.err.println("NO Image");
        }
    }


}
