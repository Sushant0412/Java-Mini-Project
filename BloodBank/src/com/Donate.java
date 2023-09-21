package com;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class Donate extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldName;
    private JTextField txtEmail;
    private JTextArea textCity;
    private Choice bldTypeChoice;
    private JTextField txtContact;

    // Database connection variables
    private Connection connection = null;
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/donatebld";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Donate frame = new Donate();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Donate() {
        setBounds(100, 100, 800, 600);
        getContentPane().setBackground(new Color(255, 0, 0));
        getContentPane().setBounds(new Rectangle(0, 0, 800, 600));
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 192, 192));
        panel.setBounds(54, 83, 276, 394);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setForeground(new Color(0, 0, 0));
        lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblName.setBounds(20, 23, 57, 35);
        panel.add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBorder(new EmptyBorder(0, 0, 0, 0));
        textFieldName.setBounds(91, 30, 159, 25);
        panel.add(textFieldName);
        textFieldName.setColumns(10);

        textCity = new JTextArea();
        textCity.setBounds(91, 84, 159, 60);
        panel.add(textCity);

        JLabel lblCity = new JLabel("Address:");
        lblCity.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCity.setBounds(20, 99, 57, 35);
        panel.add(lblCity);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblEmail.setBounds(20, 178, 57, 20);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBorder(new EmptyBorder(0, 0, 0, 0));
        txtEmail.setBounds(91, 178, 159, 25);
        panel.add(txtEmail);
        txtEmail.setColumns(10);

        bldTypeChoice = new Choice();
        bldTypeChoice.setSize(127, 18);
        bldTypeChoice.setLocation(123, 231);
        bldTypeChoice.add("Select Type");
        bldTypeChoice.add("A+");
        bldTypeChoice.add("B+");
        bldTypeChoice.add("AB+");
        bldTypeChoice.add("O+");
        bldTypeChoice.add("A-");
        bldTypeChoice.add("B-");
        bldTypeChoice.add("AB-");
        bldTypeChoice.add("O-");
        panel.add(bldTypeChoice);

        JLabel lblBloodType = new JLabel("Blood Type:");
        lblBloodType.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblBloodType.setBounds(20, 231, 97, 20);
        panel.add(lblBloodType);

        JLabel lblContact = new JLabel("Contact:");
        lblContact.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblContact.setBounds(20, 283, 69, 20);
        panel.add(lblContact);

        txtContact = new JTextField();
        txtContact.setBorder(new EmptyBorder(0, 0, 0, 0));
        txtContact.setBounds(91, 283, 159, 25);
        panel.add(txtContact);
        txtContact.setColumns(10);

        JButton btnDonate = new JButton("Donate");
        btnDonate.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        btnDonate.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDonate.setBounds(17, 329, 100, 30);
        panel.add(btnDonate);

        // Add an ActionListener to the "Donate" button
        btnDonate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the data from the form
                String name = textFieldName.getText();
                String city = textCity.getText();
                String email = txtEmail.getText();
                String bloodType = bldTypeChoice.getSelectedItem();
                String contact = txtContact.getText();

                try {
                    // Establish a database connection
                    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                    // Create an SQL INSERT statement
                    String sql = "INSERT INTO donorlist (Name, Address, Email, BloodType, Contact) VALUES (?, ?, ?, ?, ?)";

                    // Prepare the SQL statement
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, city);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, bloodType);
                    preparedStatement.setString(5, contact);

                    // Execute the SQL statement to insert the data
                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Donation data inserted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Data insertion failed.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
