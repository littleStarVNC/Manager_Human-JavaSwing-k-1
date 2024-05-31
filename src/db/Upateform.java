package db;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class Updateform extends JFrame implements ActionListener {
    JLabel lb1;
    JTextField tf1;
    JLabel lb2;
    JTextField tf2;
    JLabel lb3;
    JTextField tf3;
    JLabel lb4;
    JTextField tf4;
    JLabel er1;
    JLabel er2;

    JButton btok;
    JButton btcancel;

    QuanLyNhanSu ql;
    String id;

    public Updateform(String s, QuanLyNhanSu st, String i, String n1, String n2, String n3, String n4) {
        super(s);
        URL urlvne = Updateform.class.getResource("QL.jpg");
        Image img = Toolkit.getDefaultToolkit().createImage(urlvne);
        this.setIconImage(img);
        
        ql = st;
        id = i;
        Container cont = this.getContentPane();
        cont.setLayout(new GridLayout(6, 2));
        lb1 = new JLabel("NAME");
        tf1 = new JTextField(n1);
        lb2 = new JLabel("AGE");
        tf2 = new JTextField(n2);
        lb3 = new JLabel("POSITION");
        tf3 = new JTextField(n3);
        lb4 = new JLabel("SALARY");
        tf4 = new JTextField(n4);
        er1 = new JLabel("");
        er2 = new JLabel("");
        er1.setVisible(false);
        er2.setVisible(true);
        btok = new JButton("OK");
        btcancel = new JButton("Cancel");
        cont.add(lb1);
        cont.add(tf1);
        cont.add(lb2);
        cont.add(tf2);
        cont.add(lb3);
        cont.add(tf3);
        cont.add(lb4);
        cont.add(tf4);
        cont.add(er1);
        cont.add(er2);
        cont.add(btok);
        cont.add(btcancel);
        btok.addActionListener(this);
        btcancel.addActionListener(this);
        this.setSize(330, 300);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(173, 216, 230)); 
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")) {
            insertDbB();
        } else {
            this.dispose();
        }
    }

    public void insertDbB() {
        if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf4.getText().isEmpty()) {
            er1.setText("Error");
            er2.setText("Empty values");
            er1.setForeground(Color.RED);
            er2.setForeground(Color.RED);
            er1.setVisible(true);
            er2.setVisible(true);
        } else {
            try {
                String n1 = tf1.getText();
                String n2 = tf2.getText();
                String n3 = tf3.getText();
                String n4 = tf4.getText();

                String sql = "";
                if (this.getTitle().equals("INSERT ")) {
                    sql = "INSERT INTO MNNS(NAME, AGE, POSITION, SALARY) VALUES (?, ?, ?, ?)";
                } else {
                    if (!id.isEmpty()) {
                        sql = "UPDATE MNNS SET  NAME=?, AGE=?, POSITION=?, SALARY=? WHERE ID=?";
                    }
                }

                try (PreparedStatement pstmt = ql.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    pstmt.setString(1, n1);
                    pstmt.setString(2, n2);
                    pstmt.setString(3, n3);
                    pstmt.setString(4, n4);

                    if (!id.isEmpty()) {
                        pstmt.setString(5, id);
                    }

                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0 && this.getTitle().equals("INSERT ")) {
                        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                id = String.valueOf(generatedKeys.getInt(1));
                            }
                        }
                    }

                    ql.reload();
                    ql.model.fireTableDataChanged();
                    this.dispose();
                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                }
            } catch (Exception ez) {
                ez.printStackTrace();
            }
        }
    }
}
