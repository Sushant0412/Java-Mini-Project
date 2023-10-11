package com;


import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Login implements ActionListener{

	public JFrame frame;
	public JPasswordField passwordField;
	private  String securityNumber = "abcd";

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void openWelcomePage() {
	    frame.dispose(); // Dispose of the login frame
	    Welcome welcome = new Welcome(); // Create a new instance of the Welcome page
	    welcome.frame.setVisible(true); // Make the Welcome page visible
	}

	private boolean isLoginValid(String username, String password) {
        try {
            // Establish a database connection (replace with your database credentials)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3310/login", "root", "root");

            // Prepare a SQL query to check for a matching entry
            String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            // Check if a matching entry was found
            if (resultSet.next()) {
                // Close database resources
                resultSet.close();
                statement.close();
                connection.close();
                return true; // Valid login
            } else {
                // Close database resources
                resultSet.close();
                statement.close();
                connection.close();
                return false; // Invalid login
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database connection errors
            return false; // Invalid login due to an error
        }
    }
	
	private void initialize()  {
		  
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
		panel.setBounds(512, 121, 230, 270);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea username = new JTextArea();
		username.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		username.setBounds(22, 50, 118, 22);
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
		usernameLabel.setBounds(22, 21, 80, 19);
		panel.add(usernameLabel);
		usernameLabel.setForeground(new Color(0, 0, 0));
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(22, 94, 80, 26);
		panel.add(passLabel);
		passLabel.setForeground(new Color(0, 0, 0));
		passLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		passwordField.setBounds(22, 130, 118, 26);
		panel.add(passwordField);
		
		JButton loginbtn = new JButton("LOGIN");
		loginbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the entered username and password
                String enteredUsername = username.getText();
                char[] enteredPassword = passwordField.getPassword();
                String enteredPasswordStr = new String(enteredPassword);
                
                if (enteredUsername.isEmpty() || enteredPasswordStr.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Username and password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Check the database for a matching entry
                    if (isLoginValid(enteredUsername, enteredPasswordStr)) {
                        // Check if the username format indicates a user
                        if (enteredUsername.matches("user[1-5]")) {
                            // If the format is userNo., where No. = 1/2/3/4/5, call UserHome
                        	JOptionPane.showMessageDialog(frame, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                        	frame.dispose();
                        	userHome UserHome = new userHome();
                            UserHome.setVisible(true);
                            // Perform any necessary actions for the user home screen
                        } else {
                            // Otherwise, call the admin home (Home)
                        	JOptionPane.showMessageDialog(frame, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                        	frame.dispose();
                        	Home home = new Home();
                            home.setVisible(true);
                            // Perform any necessary actions for the admin home screen
                        }
                    } else {
                        // Handle the case where the login is invalid
                        JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

	    // Function to check if the login is valid
		
		
		loginbtn.setActionCommand("Home");
		loginbtn.setBounds(22, 189, 93, 35);
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
		btnLogout.setBounds(125, 189, 93, 35);
		panel.add(btnLogout);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(true);
		
	}
}
