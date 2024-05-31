package Gate;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.Home;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;

public class Signin extends JFrame {

private JPanel jPane;
private JTextField tften;
private JPasswordField tfmk;
private JPasswordField tfma;

public Signin() {
        	            URL imaQL = Signin.class.getResource("QL.jpg");
                    Image img = Toolkit.getDefaultToolkit().createImage(imaQL);
                    this.setIconImage(img);
/*     URL đang được sử dụng để tìm kiếm tài nguyên có tên "QL.jpg" trong cùng gói (package) với class START..
      
    Sử dụng Toolkit.getDefaultToolkit() giúp đảm bảo rằng bạn đang sử dụng một thể hiện của Toolkit phù hợp với hệ điều hành đó..*/

                    
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             setSize(460, 260);
             setLocationRelativeTo(null);
             jPane = new JPanel();
             jPane.setBackground(new Color(192, 192, 192));

             add(jPane);
             jPane.setLayout(null);

                 JLabel lblwlcom = new JLabel("Welcome HUMAN RESOURCES MANAGER");
                 lblwlcom.setBounds(102, 10, 244, 13);
                 jPane.add(lblwlcom);

                 JLabel lblplease = new JLabel("Please log in to your Administrator account !");
                 lblplease.setBounds(10, 43, 260, 13);
                 jPane.add(lblplease);

                 JLabel lblname = new JLabel("Name :");
                 lblname.setBounds(23, 88, 45, 13);
                 jPane.add(lblname);

                 tften = new JTextField();
                 tften.setBounds(102, 85, 172, 19);
                 jPane.add(tften);
                 

                 JLabel lblmk = new JLabel("Password :");
                 lblmk.setBounds(23, 126, 69, 13);
                 jPane.add(lblmk);

                 tfmk = new JPasswordField();
                 tfmk.setBounds(102, 123, 172, 19);
                 jPane.add(tfmk);

                 JLabel lblmaxn = new JLabel("Your Verification Code :");
                 lblmaxn.setBounds(50, 169, 172, 13);
                 jPane.add(lblmaxn);

                 tfma = new JPasswordField();
                 tfma.setBounds(215, 166, 105, 19);
                 jPane.add(tfma);

              // Thêm RoundButton vào mã nguồn
                SuperButton.RoundButton btnLogin = new SuperButton.RoundButton("Login");
                btnLogin.setForeground(Color.WHITE);
                btnLogin.setBackground(new Color(0, 102, 204));
                btnLogin.setBounds(326, 88, 110, 35);
                jPane.add(btnLogin);
                
                btnLogin.addActionListener(new ActionListener() {
			
		    	public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {

		           
		            
              String url = "jdbc:sqlserver://localhost:1433;databaseName=Nhansu;encrypt=true;user=sa;password=21112005;encrypt=true;trustServerCertificate=true";
		      Connection conn = DriverManager.getConnection(url);
		      
		   /* Dòng  Class.forName đang đăng ký một JDBC driver cho SQL Server.
		    sử dụng Class.forName để động cơ JDBC có thể tìm và tải lớp trình điều khiển của SQL Server vào bộ nhớ
		   localhost (máy chủ cục bộ)    
		      encrypt=true: Sử dụng kết nối được mã hóa.
		trustServerCertificate=true: Chấp nhận chứng chỉ từ máy chủ SQL Server.*/
	
		      
		      String query = "SELECT * FROM Signin WHERE Tên=? AND [Mật Khẩu]=? AND [Mã Xác nhận]=?";
              PreparedStatement pre = conn.prepareStatement(query);
		           pre.setString(1, tften.getText());
		           pre.setString(2, tfmk.getText());
		           pre.setString(3, tfma.getText());
		           
            ResultSet rs = pre.executeQuery();
		            if(rs.next()) {// rs sẽ trả về true và false
		            	Home house = new Home();
		            	 dispose();//Đóng cửa sổ hiện tại, giải phóng tài nguyên của nó.
		            	 conn.close();// đóng kết nối để tránh rò rỉ dữ liệu , giảm dung lượng bộ nhớ
		            }else {
		            	JOptionPane.showMessageDialog(null, "Login Failed!!");
		            }
				} catch (Exception e2) {
					e2.printStackTrace();
				    JOptionPane.showMessageDialog(null, "ERROR !!!!");			
				    }
			}
		});
       
    }
           

      public static void main(String[] args) {
    	  
          EventQueue.invokeLater(new Runnable() {
        	  
             public void run() {// thực thi trong này để tránh tình trang lq bảo mật
              try {
                     Signin si = new Signin();
                            si.setTitle("LOGIN");
                            si.setVisible(true);
                            si.setDefaultCloseOperation(EXIT_ON_CLOSE);
            } catch (Exception e) {
                      e.printStackTrace();
              }

        }

     });
   }
}
