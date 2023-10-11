package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class adminLogin {

	private JFrame frame;
	private JTextField usernameTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminLogin window = new adminLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public adminLogin() {
		initialize();
	}

	private void openWelcomePage() {
	    frame.dispose(); // Dispose of the login frame
	    Welcome welcome = new Welcome(); // Create a new instance of the Welcome page
	    welcome.frame.setVisible(true); // Make the Welcome page visible
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login Page");
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(213, 213, 213));
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBounds(513, 131, 230, 292);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea username = new JTextArea();
		username.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		username.setBounds(22, 96, 118, 22);
		panel.add(username);
		
		ImageIcon backgroundImage = new ImageIcon("images/loginBg.jpg"); // Replace with your image path
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBorder(null);
		backgroundLabel.setBounds(0, 0, 786, 563); // Set the size to match your frame size
		frame.getContentPane().add(backgroundLabel);

		JLabel titleLabel = new JLabel("Blood Bank Management System");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 0, 0)); // Set the text color
        titleLabel.setBounds(50, 50, 500, 40); // Adjust position and size as needed
        backgroundLabel.add(titleLabel); // Add to the background label
		
		JLabel usernameLabel = new JLabel("UserName");
		usernameLabel.setBounds(22, 67, 80, 19);
		panel.add(usernameLabel);
		usernameLabel.setForeground(new Color(0, 0, 0));
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(22, 141, 80, 26);
		panel.add(passLabel);
		passLabel.setForeground(new Color(0, 0, 0));
		passLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		passwordField.setBounds(22, 177, 118, 26);
		panel.add(passwordField);
		
		JButton loginbtn = new JButton("LOGIN");
		loginbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        // Get the entered username and password
				
				String enteredUsername = username.getText(); // Implement this method to retrieve the username.
		        String enteredPassword = passwordField.getText(); // Implement this method to retrieve the password.

		        // Check if the entered username and password are correct
		        if ("admin".equals(enteredUsername) && "12345".equals(enteredPassword)) {
		            // If the credentials are correct, open an instance of Home
		        	JOptionPane.showMessageDialog(frame, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                	frame.dispose();
                	Home admin = new Home();
                    admin.setVisible(true); // Make the Home frame visible
		        } else if("admin".equals(enteredUsername) || "12345".equals(enteredPassword)){
		            // Handle the case where the login is invalid, show an error message, etc.
		        	JOptionPane.showMessageDialog(frame, "Invalid Username or password", "Error", JOptionPane.ERROR_MESSAGE);
		        	username.setText(""); 
			        passwordField.setText("");
		        }
		        else {
		        	JOptionPane.showMessageDialog(frame, "Invalid Username or password", "Error", JOptionPane.ERROR_MESSAGE);
		        	username.setText(""); 
			        passwordField.setText("");
		        }
		    }
        });

	    // Function to check if the login is valid
		
		
		loginbtn.setActionCommand("Home");
		loginbtn.setBounds(23, 232, 93, 35);
		panel.add(loginbtn);
		loginbtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginbtn.setForeground(new Color(0, 0, 0));
		loginbtn.setBackground(new Color(255, 255, 0));
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnLogout) {
					openWelcomePage();
				}
			}
		});
		btnLogout.setForeground(Color.BLACK);
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogout.setBackground(Color.YELLOW);
		btnLogout.setActionCommand("Home");
		btnLogout.setBounds(127, 232, 93, 35);
		panel.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("ADMIN LOGIN");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(60, 28, 110, 13);
		panel.add(lblNewLabel);
	}

}
