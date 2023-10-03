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
    private JTextArea textAddress;
    private JTextField txtEnterCity;
    private Choice cityChoice;
    private Image backgroundImage;
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
        getContentPane().setBackground(new Color(0, 64, 128));
        getContentPane().setBounds(new Rectangle(0, 0, 800, 600));
        getContentPane().setLayout(null);


        
        JPanel enterDetail = new JPanel();
        enterDetail.setBackground(new Color(192, 192, 192)); // 0 for fully transparent
        enterDetail.setBounds(40, 108, 276, 402);
        getContentPane().add(enterDetail);
        enterDetail.setLayout(null);

        cityChoice = new Choice();
        cityChoice.setBounds(107, 82, 143, 18);
        cityChoice.add("Select City");
        cityChoice.add("Kurla");
        cityChoice.add("Chembur");
        cityChoice.add("Sion");
        cityChoice.add("Ghatkoper");
        cityChoice.add("Vashi");
        cityChoice.setForeground(Color.BLACK);
        enterDetail.add(cityChoice);

        txtEnterCity = new JTextField();
        txtEnterCity.setBounds(20, 81, 96, 19);
        txtEnterCity.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtEnterCity.setBorder(new CompoundBorder());
        txtEnterCity.setBackground(new Color(192, 192, 192));
        txtEnterCity.setText("Enter City:");
        enterDetail.add(txtEnterCity);
        txtEnterCity.setColumns(10);
        
        JLabel lblName = new JLabel("Name:");
        lblName.setForeground(new Color(0, 0, 0));
        lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblName.setBounds(20, 23, 57, 35);
        enterDetail.add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBorder(new EmptyBorder(0, 0, 0, 0));
        textFieldName.setBounds(91, 30, 159, 25);
        enterDetail.add(textFieldName);
        textFieldName.setColumns(10);

        textAddress = new JTextArea();
        textAddress.setLineWrap(true); // Enable text wrapping
        textAddress.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(textAddress); // Add a scroll pane
        scrollPane.setBounds(91, 130, 159, 60);
        enterDetail.add(scrollPane); // Add the scroll pane to the panel

        JLabel lblCity = new JLabel("Address:");
        lblCity.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCity.setBounds(20, 145, 57, 35);
        enterDetail.add(lblCity);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblEmail.setBounds(20, 228, 57, 20);
        enterDetail.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBorder(new EmptyBorder(0, 0, 0, 0));
        txtEmail.setBounds(91, 228, 159, 25);
        enterDetail.add(txtEmail);
        txtEmail.setColumns(10);

        bldTypeChoice = new Choice();
        bldTypeChoice.setSize(127, 18);
        bldTypeChoice.setLocation(123, 275);
        bldTypeChoice.add("Select Type");
        bldTypeChoice.add("A+");
        bldTypeChoice.add("B+");
        bldTypeChoice.add("AB+");
        bldTypeChoice.add("O+");
        bldTypeChoice.add("A-");
        bldTypeChoice.add("B-");
        bldTypeChoice.add("AB-");
        bldTypeChoice.add("O-");
        enterDetail.add(bldTypeChoice);

        JLabel lblBloodType = new JLabel("Blood Type:");
        lblBloodType.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblBloodType.setBounds(20, 275, 97, 20);
        enterDetail.add(lblBloodType);

        JLabel lblContact = new JLabel("Contact:");
        lblContact.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblContact.setBounds(20, 314, 69, 20);
        enterDetail.add(lblContact);

        txtContact = new JTextField();
        txtContact.setBorder(new EmptyBorder(0, 0, 0, 0));
        txtContact.setBounds(91, 314, 159, 25);
        enterDetail.add(txtContact);
        txtContact.setColumns(10);

        JButton btnDonate = new JButton("Donate");
        btnDonate.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        btnDonate.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDonate.setBounds(20, 362, 100, 30);
        enterDetail.add(btnDonate);
        
        JButton btnNewButton = new JButton("Delete");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String deleteName = JOptionPane.showInputDialog(null, "Enter Name:");
                String deleteBloodType = JOptionPane.showInputDialog(null, "Enter Blood Type:");

                if (deleteName != null && deleteBloodType != null) {
                    try {
                        // Establish a database connection
                        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                        // Create an SQL DELETE statement
                        String deleteSql = "DELETE FROM donorlist WHERE Name = ? AND BloodType = ?";

                        // Prepare the SQL statement
                        PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
                        deleteStatement.setString(1, deleteName);
                        deleteStatement.setString(2, deleteBloodType);

                        // Execute the SQL statement to delete the data
                        int rowsDeleted = deleteStatement.executeUpdate();

                        if (rowsDeleted > 0) {
                            JOptionPane.showMessageDialog(null, "Entry deleted successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null, "No matching entry found for deletion.");
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
            }
        });
        btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setBounds(151, 362, 99, 28);
        enterDetail.add(btnNewButton);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 0, 0));
        panel.setBounds(40, 10, 718, 54);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Provide Donor Details");
        lblNewLabel.setBounds(158, 5, 394, 39);
        panel.add(lblNewLabel);
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel imgPanel = new JPanel();
        imgPanel.setBorder(new CompoundBorder(new CompoundBorder(), null));
        imgPanel.setBounds(new Rectangle(0, 0, 400, 400));
        imgPanel.setBounds(358, 108, 400, 400);
        getContentPane().add(imgPanel);
        
     // Create a JLabel for the quote image
        JLabel quoteImageLabel = new JLabel(new ImageIcon("images/donateQuote.jpg"));
        quoteImageLabel.setBounds(0, 0, 400, 400); // Adjust the position and size as needed
        imgPanel.add(quoteImageLabel); // Add the label to the imgPanel

        // Add an ActionListener to the "Donate" button
        btnDonate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the data from the form
                String name = textFieldName.getText();
                String address = textAddress.getText();
                String email = txtEmail.getText();
                String bloodType = bldTypeChoice.getSelectedItem();
                String contact = txtContact.getText();
                String city = cityChoice.getSelectedItem();

                try {
                    // Establish a database connection
                    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                    // Create an SQL INSERT statement
                    String sql = "INSERT INTO donorlist (Name, City, Address, Email, BloodType, Contact) VALUES (?, ?, ?, ?, ?, ?)";

                    // Prepare the SQL statement
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, city);
                    preparedStatement.setString(3, address);
                    preparedStatement.setString(4, email);
                    preparedStatement.setString(5, bloodType);
                    preparedStatement.setString(6, contact);

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
