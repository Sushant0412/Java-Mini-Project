package com;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class Search extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField title;
	private JTextField txtEnterCity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Search() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 766, 543);
		panel.setBackground(new Color(255, 0, 0));
		panel.setForeground(new Color(255, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 88, 533, 39);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSearchBlood = new JLabel("Search Blood Stock");
		lblSearchBlood.setBounds(10, 0, 497, 39);
		panel_1.add(lblSearchBlood);
		lblSearchBlood.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		Choice choice = new Choice();
		choice.add("Select City");
		choice.add("Kurla");
		choice.add("Chembur");
		choice.add("Sion");
		choice.add("Ghatkoper");
		choice.add("Vashi");
		choice.setForeground(Color.BLACK);
		choice.setBounds(87, 200, 104, 34);
		panel.add(choice);
		
		txtEnterCity = new JTextField();
		txtEnterCity.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEnterCity.setBorder(new CompoundBorder());
		txtEnterCity.setBackground(new Color(255, 0, 0));
		txtEnterCity.setText("Enter City:");
		txtEnterCity.setBounds(10, 199, 96, 19);
		panel.add(txtEnterCity);
		txtEnterCity.setColumns(10);
		
		JTextArea stockOutput = new JTextArea();
		stockOutput.setFont(new Font("Tahoma", Font.BOLD, 15));
		stockOutput.setText("Available Donors: ");
		stockOutput.setBounds(252, 158, 476, 345);
		panel.add(stockOutput);
		
		JButton btnNewButton = new JButton("Enter");
		// Inside your ActionListener for btnNewButton
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String selectedCity = choice.getSelectedItem(); // Get the selected city from the Choice component
		            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/search", "root", "root");
		            
		            // Create a PreparedStatement with a parameterized query
		            String query = "SELECT * FROM blood WHERE city = ?";
		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setString(1, selectedCity);

		            // Execute the query
		            ResultSet resultSet = preparedStatement.executeQuery();

		            // Initialize a StringBuilder to build the table
		            StringBuilder table = new StringBuilder();

		            // Add table headers
		            table.append("          City\t |       ID        |     Blood Type\n");
		            
		            table.append("----------------------------------------------------------\n");

		            // Process the result set and add rows to the table
		            while (resultSet.next()) {
		                String city = resultSet.getString("city");
		                String id = resultSet.getString("id");
		                String bloodType = resultSet.getString("blood_type");

		                // Append data to the table with tabs as separators
		                table.append("          ").append(city).append("\t |       ").append(id).append("          |            ").append(bloodType).append("\n");
		            }

		            // Close the database resources
		            resultSet.close();
		            preparedStatement.close();
		            connection.close();

		            // Update your text area (JTextArea) with the formatted table
		            stockOutput.setText("\tAvailable Donors: \n" + table.toString());
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});


		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(87, 237, 85, 21);
		panel.add(btnNewButton);
		
		title = new JTextField();
		title.setBounds(10, 10, 553, 62);
		panel.add(title);
		title.setFont(new Font("Tahoma", Font.BOLD, 15));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setText("Blood Stock Availibility");
		title.setColumns(10);
		
	}
}
