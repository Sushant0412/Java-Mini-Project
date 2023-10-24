package com;

import java.awt.*;
import java.util.*;
import java.text.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
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
    private JTextField txtDOB;

    // Database connection variables
    private Connection connection = null;
    private static final String DB_URL = "jdbc:mysql://localhost:3310/bloodbank";
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
        enterDetail.setBounds(40, 108, 276, 428);
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
        JScrollPane scrollPane = new JScrollPane(); // Add a scroll pane
        scrollPane.setBounds(91, 110, 159, 64);
        enterDetail.add(scrollPane); // Add the scroll pane to the panel
        
        textAddress = new JTextArea();
        scrollPane.setViewportView(textAddress);
        textAddress.setLineWrap(true); // Enable text wrapping
        textAddress.setWrapStyleWord(true); // Wrap at word boundaries

        JLabel lblCity = new JLabel("Address:");
        lblCity.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCity.setBounds(20, 123, 57, 35);
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

        JLabel lblDOB = new JLabel("Date of Birth:");
        lblDOB.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDOB.setBounds(20, 188, 115, 20);
        enterDetail.add(lblDOB);

     // Create a JTextField for DOB with placeholder
        txtDOB = new JTextField();
        txtDOB.setBorder(new EmptyBorder(0, 0, 0, 0));
        txtDOB.setBounds(137, 188, 113, 25);
        txtDOB.setForeground(Color.GRAY); // Set text color to gray
        txtDOB.setText("YYYY-MM-dd"); // Placeholder text
        enterDetail.add(txtDOB);
        txtDOB.setColumns(10);

        // Add a focus listener to handle the placeholder text
        txtDOB.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtDOB.getText().equals("YYYY-MM-dd")) {
                    txtDOB.setText("");
                    txtDOB.setForeground(Color.BLACK); // Change text color to black when the field is focused
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtDOB.getText().isEmpty()) {
                    txtDOB.setText("yyyy-MM-dd");
                    txtDOB.setForeground(Color.GRAY); // Change text color back to gray when there's no input
                }
            }
        });


        
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
                        String deleteSql = "DELETE FROM donor WHERE donor_name = ? AND blood_Type = ?";

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
        panel.setBounds(138, 10, 620, 54);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Provide Donor Details");
        lblNewLabel.setBounds(10, 5, 627, 39);
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
        
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource() == backBtn) {
        			dispose();
        			Home launch = new Home();
        			launch.setVisible(true);        		}
        	}
        });
        backBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        backBtn.setBounds(40, 10, 85, 54);
        getContentPane().add(backBtn);

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
                String dob = txtDOB.getText();
                
                if (name.isEmpty() || address.isEmpty() || email.isEmpty() || dob.isEmpty() || bloodType.equals("Select Type") || contact.isEmpty() || city.equals("Select City")) {
                    JOptionPane.showMessageDialog(null, "Please enter all the details.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                	java.sql.Date birthDate = new java.sql.Date(dateFormat.parse(dob).getTime());
                    Calendar dobCalendar = Calendar.getInstance();
                    dobCalendar.setTime(birthDate);

                    Calendar currentCalendar = Calendar.getInstance();
                    int age = currentCalendar.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);

                    // If the user is under 18, show an error message
                    if (age < 18) {
                        JOptionPane.showMessageDialog(null, "Sorry, you are not eligible to donate blood as you are under 18 years old.");
                    } else {
                        // Continue with the database insertion
                        try {
                            // Establish a database connection
                            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                            // Create an SQL INSERT statement
                            String sql = "INSERT INTO donor (donor_name, City, Address, Email, blood_Type, dr_contact, DOB) VALUES (?, ?, ?, ?, ?, ?, ?)";

                            // Prepare the SQL statement
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setString(1, name);
                            preparedStatement.setString(2, city);
                            preparedStatement.setString(3, address);
                            preparedStatement.setString(4, email);
                            preparedStatement.setString(5, bloodType);
                            preparedStatement.setString(6, contact);
                            preparedStatement.setDate(7, birthDate);

                            // Execute the SQL statement to insert the data
                            int rowsInserted = preparedStatement.executeUpdate();

                            if (rowsInserted > 0) {
                                JOptionPane.showMessageDialog(null, "Donation data inserted successfully.");

                                // Clear input fields and reset choice boxes
                                textFieldName.setText("");
                                textAddress.setText("");
                                txtEmail.setText("");
                                bldTypeChoice.select(0); // Reset the choice box to the default value
                                txtContact.setText("");
                                cityChoice.select(0); // Reset the choice box to the default value
                                txtDOB.setText("YYYY-MM-dd");
                                txtDOB.setForeground(Color.GRAY);
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
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Invalid date of birth format. Please use yyyy-MM-dd format.");
                }
                }
            }
        });

    }
}
