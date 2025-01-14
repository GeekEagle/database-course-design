package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
public class Jstudentupdata extends JFrame{
    public Jstudentupdata() {
        this.setSize(1000,800);
        this.setTitle("学生信息更新");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel txt1 = new JLabel("输入所查学生学号：");
        JTextField txtSno = new JTextField(20);
        pane.add(txt1);
        pane.add(txtSno);
        String[] listname= {"学号","姓名","专业","年龄",
                "政治面貌","导师","班级号","学院",
                "年纪","学生状态","籍贯","志愿时长",
                "学费","是否缴费","绩点"};

        JButton btnOK=new JButton("确定");
        pane.add(btnOK);
        JFrame frame=this;
        btnOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(txtSno.getText().length()==0) {
                    JOptionPane.showMessageDialog(frame, "学号必填！");
                    return;
                }
                // Create a variable for the connection string.
                String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";

                // Declare the JDBC objects.
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                String[][] count=new String[1][15];
                try {
                    // Establish the connection.
                    //com.microsoft.jdbc.Sqlserver.SQLServerDriver
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                    // Create and execute an SQL statement that returns some data.
                    String SQL = "select * from 学生信息表_view where StuID="+txtSno.getText();
                    stmt = con.createStatement();
                    int i=0;
                    rs =stmt.executeQuery(SQL);
                    if(rs.next())
                    {
                        Jstudentupdataresult f = new Jstudentupdataresult(txtSno.getText());
                        f.setVisible(true);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "查无此学生！");
                    }
                }
                // Iterate through the data in the result set and display it.
                // Handle any errors that may have occurred.
                catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (rs != null)
                        try {
                            rs.close();
                        } catch (Exception e1) {
                        }
                    if (stmt != null)
                        try {
                            stmt.close();
                        } catch (Exception e2) {
                        }
                    if (con != null)
                        try {
                            con.close();
                        } catch (Exception e3) {
                        }
                }
            }

        });
        JButton btnClose = new JButton("退出");
        pane.add(btnClose);
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }

        });
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}