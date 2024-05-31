package db;

import java.awt.*;

import java.awt.event.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Function.Deleted;
import Function.DeletedTable;
import Function.SearchResultFrame;
import Gate.Signin;
import Image_NV.BackgroundPanel;
import Image_NV.nv1;
import Image_NV.nv2;
import Image_NV.nv3;
import Image_NV.nv4;
import Image_NV.nv5;
import Image_NV.nv6;
import Image_NV.nv7;
import Image_NV.nv8;
import SuperButton.RoundButton;



public class QuanLyNhanSu extends JFrame implements ActionListener, MouseListener {

    Connection con;
    Statement stm;
    ResultSet rs;

    Vector<Vector<String>> vData = new Vector<>();
    Vector<String> vTitle = new Vector<>();

    JScrollPane jscollpane;
    DefaultTableModel model;
     public static  JTable tb = new JTable();

    RoundButton edit, add, delete, search; 
    JButton btback;

    
    JPanel p;
    BackgroundPanel backgroundPanel;

    JMenuBar jmenubar;
    JMenu mn;
    JMenu mnDelete;

    JMenuItem jmenuitHome;
    JMenuItem jmenuitSearch;
    JMenuItem jmenuitExit;
    JMenu mnSearchSalary;
    JMenuItem jmenuitBySalary;

    int selectedRow = 0;

    List<Deleted> deletedRows = new ArrayList<>();
  
    
    public QuanLyNhanSu(String s) {
   
        try {
        	  
            URL urlvne = QuanLyNhanSu.class.getResource("QL.jpg");
            Image img = Toolkit.getDefaultToolkit().createImage(urlvne);

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(
            		"jdbc:sqlserver://localhost:1433;databaseName=Nhansu;user=sa;password=21112005;encrypt=true;trustServerCertificate=true");
            stm = con.createStatement();

           

            p = new JPanel(new FlowLayout(FlowLayout.CENTER, 96, 180));
            p.setOpaque(false);// Đặt chế độ trong suốt cho JLabel

            edit = new RoundButton("EDIT");
            edit.setForeground(Color.BLUE);
            edit.addActionListener(this);

            add = new RoundButton("ADD");
            add.setForeground(Color.GREEN);
            add.addActionListener(this);

            delete = new RoundButton("DELETE");
            delete.setForeground(Color.RED);
            delete.addActionListener(this);

       

            btback = new JButton("BACK!");
            btback.setForeground(Color.BLACK);

            jmenubar = new JMenuBar();
            mn = new JMenu("View");

            jmenuitHome = new JMenuItem("Home");
            jmenuitSearch = new JMenuItem("Search");
            jmenuitExit = new JMenuItem("Exit");
            mn.add(jmenuitHome);
            
        
          
            mnSearchSalary = new JMenu("Search Salary");
            jmenuitBySalary = new JMenuItem("By Salary Range");
            mn.add(jmenuitSearch);
          
            mn.add(jmenuitBySalary);
            mn.addSeparator();
            mn.add(jmenuitExit);

            mnDelete = new JMenu("Deleted");
            JMenuItem vDeletedItem = new JMenuItem("View Deleted");
            mnDelete.add(vDeletedItem);
            
            jmenubar.add(mn);
            jmenubar.add(mnDelete);
            
            this.setJMenuBar(jmenubar);
            
            p.add(edit);
            p.add(add);
            p.add(delete);
            p.add(btback);
 
            
            backgroundPanel = new BackgroundPanel();
            
            backgroundPanel.setLayout(new BorderLayout());
            backgroundPanel.add(p, BorderLayout.CENTER);

            reload();
            model = new DefaultTableModel(vData, vTitle);
            tb = new JTable(model);
            tb.addMouseListener(this);

            jscollpane = new JScrollPane(tb);
            this.add(jscollpane, BorderLayout.NORTH);
             this.add(backgroundPanel,BorderLayout.CENTER);
            

            this.setExtendedState(MAXIMIZED_BOTH);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);

            jmenuitHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Home house = new Home();
                    house.setVisible(true);
                }
            });
            jmenuitBySalary.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JFrame fr = new JFrame("Search by Salary Range");
                        fr.setLayout(new BorderLayout());

                        JLabel lbMin = new JLabel("Min Salary:");
                        JTextField tfMin = new JTextField(10);

                        JLabel lbMax = new JLabel("Max Salary:");
                        JTextField tfMax = new JTextField(10);

                        JButton btnSearchSalary = new JButton("Search");

                        JPanel jPanel = new JPanel(new GridLayout(3, 2));
                        jPanel.add(lbMin);
                        jPanel.add(tfMin);
                        jPanel.add(lbMax);
                        jPanel.add(tfMax);
                        jPanel.add(btnSearchSalary);

                        fr.add(jPanel, BorderLayout.NORTH);

                        fr.setSize(280, 200);
                        fr.setLocationRelativeTo(null);
                        fr.setVisible(true);

                        btnSearchSalary.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    PreparedStatement pre = con.prepareStatement("SELECT * FROM MNNS WHERE SALARY BETWEEN ? AND ?");
                                    pre.setString(1, tfMin.getText());
                                    pre.setString(2, tfMax.getText());

                                    ResultSet rs = pre.executeQuery();

                                    DefaultTableModel searchResultModel = new DefaultTableModel();

                                    ResultSetMetaData rsmd = rs.getMetaData();
                                    int columnCount = rsmd.getColumnCount();

                                    for (int i = 1; i <= columnCount; i++) {
                                        searchResultModel.addColumn(rsmd.getColumnName(i));
                                    }

                                    if (rs.next()) {
                                        do {
                                            Object[] row = new Object[columnCount];
                                            for (int i = 1; i <= columnCount; i++) {
                                                row[i - 1] = rs.getObject(i);
                                            }
                                            searchResultModel.addRow(row);
                                        } while (rs.next());

                                        SearchResultFrame resultFrame = new SearchResultFrame(searchResultModel);
                                        resultFrame.setVisible(true);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Data not found!!");
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            jmenuitSearch.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        URL urlvne = QuanLyNhanSu.class.getResource("QL.jpg");
                        Image img = Toolkit.getDefaultToolkit().createImage(urlvne);

                        JFrame fr = new JFrame("Result Search! ");
                        fr.setLayout(new BorderLayout());
                        fr.setIconImage(img);

                        JLabel lbpos = new JLabel("Position :");
                        JTextField tfpos = new JTextField(20);
                        JButton btnSearch = new JButton("Search");
                       
                        JPanel jPanel = new JPanel(new GridLayout(2, 1));
                        jPanel.add(lbpos);
                        jPanel.add(tfpos);

                        fr.add(jPanel, BorderLayout.NORTH);
                        fr.add(btnSearch, BorderLayout.SOUTH);
                       

                        fr.setSize(280, 200);
                        fr.setLocationRelativeTo(null);
                        fr.setVisible(true);

                        btnSearch.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                	
                                    PreparedStatement pre = con.prepareStatement("SELECT * FROM MNNS WHERE POSITION=?");
                                    pre.setString(1, tfpos.getText());
                                    ResultSet rs = pre.executeQuery();

                                    DefaultTableModel searchResultModel = new DefaultTableModel();

                                    
                                    ResultSetMetaData rsmd = rs.getMetaData();//ResultSetMetaData ở đây tạo các cột cho bảng tim kiếm
                                    int columnCount = rsmd.getColumnCount();

                                    for (int i = 1; i <= columnCount; i++) {
                                        searchResultModel.addColumn(rsmd.getColumnName(i));
                                    }

                                    if (rs.next()) {
                                    	
                                    	do {
                                            Object[] row = new Object[columnCount];
                                            for (int i = 1; i <= columnCount; i++) {
                                                row[i - 1] = rs.getObject(i);
                                            }
                                            searchResultModel.addRow(row);
                                        }while (rs.next()); 

                                        SearchResultFrame resultFrame = new SearchResultFrame(searchResultModel);
                                        resultFrame.setVisible(true);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Data not found!!");
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            jmenuitExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });


            vDeletedItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DeletedTable viewDeleted = new DeletedTable(deletedRows);
                    viewDeleted.display();
                }
            });

            

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        
        btback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resut = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?", "NOTE!",JOptionPane.OK_CANCEL_OPTION);
                        
                if (resut == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        
    }

    
    public void reload() {
        try {
            vTitle.clear();
            vData.clear();

            ResultSet rs = stm.executeQuery("SELECT * FROM MNNS");
            ResultSetMetaData rstmeta = rs.getMetaData();
            int numcolumn = rstmeta.getColumnCount();

            for (int i = 1; i <= numcolumn; i++) {
                vTitle.add(rstmeta.getColumnLabel(i));
            }

            while (rs.next()) {
                Vector<String> row = new Vector<>(numcolumn);
                for (int i = 1; i <= numcolumn; i++) {
                    row.add(rs.getString(i));
                }

                vData.add(row);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    
    public void delete() {
        try {
            if (selectedRow >= 0 ) {
                Vector<String> vt = vData.elementAt(selectedRow);

                Deleted deletedRow = new Deleted();
                deletedRow.setId(vt.elementAt(0));
                deletedRow.setName(vt.elementAt(1));
                deletedRow.setAge(vt.elementAt(2));
                deletedRow.setPosition(vt.elementAt(3));
                deletedRow.setSalary(vt.elementAt(4));
                deletedRows.add(deletedRow);

                String sql = "DELETE FROM MNNS WHERE ID='" + vt.elementAt(0) + "'";
                stm.executeUpdate(sql);
                vData.remove(selectedRow);
                model.fireTableDataChanged();

            } else {
                JOptionPane.showMessageDialog(null, "ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("DELETE")) {
            delete();
        } else if (e.getActionCommand().equals("ADD")) {
            new Updateform("INSERT ", this, "", "", "", "", "");
        } else if (e.getActionCommand().equals("EDIT")) {
            Vector<String> vt = vData.elementAt(selectedRow);
            Updateform u = new Updateform("EDIT ", this, vt.elementAt(0), vt.elementAt(1), vt.elementAt(2),
                    vt.elementAt(3), vt.elementAt(4));
        } 
    }

    public void mouseClicked(MouseEvent e) {
        selectedRow = tb.getSelectedRow();

        if (selectedRow == 0) {
            new nv1();
        } else if (selectedRow == 1) {
            new nv2();
        } else if (selectedRow == 2) {
            new nv3();
        } else if (selectedRow == 3) {
            new nv4();
        } else if (selectedRow == 4) {
            new nv5();
        } else if (selectedRow == 5) {
            new nv6();
        } else if (selectedRow == 6) {
            new nv7();
        } else if (selectedRow == 7) {
            new nv8();
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

  

    public static void main(String[] args) {
        new QuanLyNhanSu("MANAGE");
    }
}
