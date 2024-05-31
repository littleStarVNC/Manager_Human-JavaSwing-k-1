package Function;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchResultFrame extends JFrame {
	
	
    public SearchResultFrame(DefaultTableModel model) {
    	  
    	  URL urlvne = SearchResultFrame.class.getResource("QL.jpg");
          Image img = Toolkit.getDefaultToolkit().createImage(urlvne);
          setIconImage(img);
          
        JTable resultTable = new JTable(model);
        JScrollPane resultScrollPane = new JScrollPane(resultTable);

        getContentPane().add(resultScrollPane);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setTitle("Search Result :");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    }
}
