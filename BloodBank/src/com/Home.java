package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
//import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

        // Set the content pane as non-opaque to allow the background image to be visible
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 128, 64));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 10, 766, 84);
		contentPane.add(panel);
		panel.setLayout(null);
		
		ImageIcon searchIcon = new ImageIcon("C:\\Users\\susha\\eclipse-workspace\\BloodBank\\images\\search_icon.png");
		ImageIcon donateIcon = new ImageIcon("C:\\Users\\susha\\eclipse-workspace\\BloodBank\\images\\donate_icon.png");
		ImageIcon aboutUsIcon = new ImageIcon("C:\\Users\\susha\\eclipse-workspace\\BloodBank\\images\\about_icon.png");
		ImageIcon logoutIcon = new ImageIcon("C:\\Users\\susha\\eclipse-workspace\\BloodBank\\images\\logout_icon.png");
		
		
		JButton btnFind = new JButton("Looking For Blood", searchIcon);
		btnFind.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnFind.setVerticalTextPosition(SwingConstants.CENTER);
		btnFind.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		ImageIcon scaledSearchIcon = new ImageIcon(searchIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
		btnFind.setIcon(scaledSearchIcon);

		// Increase the space between the icon and text
		btnFind.setMargin(new Insets(2, 10, 2, 10)); // Adjust the Insets as needed
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnFind) {
					Search find = new Search();
					find.setVisible(true);
				}
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnFind.setBounds(363, 10, 132, 64);
		panel.add(btnFind);
		
		JButton btnDonate = new JButton("Donate Blood", donateIcon);
		btnDonate.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnDonate.setVerticalTextPosition(SwingConstants.CENTER);
		btnDonate.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		ImageIcon scaleddonateIcon = new ImageIcon(donateIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
		btnDonate.setIcon(scaleddonateIcon);

		// Increase the space between the icon and text
		btnDonate.setMargin(new Insets(2, 10, 2, 10)); // Adjust the Insets as needed
		btnDonate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnDonate) {
					Donate donate = new Donate();
					donate.setVisible(true);
				}
			}
		});
		btnDonate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDonate.setBounds(505, 10, 113, 64);
		panel.add(btnDonate);
		
		JButton logOut = new JButton("Logout", logoutIcon);
		logOut.setHorizontalTextPosition(SwingConstants.RIGHT);
		logOut.setVerticalTextPosition(SwingConstants.CENTER);
		logOut.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		ImageIcon scaledlogoutIcon = new ImageIcon(logoutIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
		logOut.setIcon(scaledlogoutIcon);

		// Increase the space between the icon and text
		logOut.setMargin(new Insets(2, 10, 2, 10)); // Adjust the Insets as needed
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == logOut)
				{
					dispose(); // Close the current Home frame	
		            Login loginPage = new Login();
		            loginPage.setVisible(true);
				}
			}
		});
		logOut.setFont(new Font("Tahoma", Font.BOLD, 12));
		logOut.setBounds(628, 10, 113, 64);
		panel.add(logOut);
		
		JButton btnAboutUs = new JButton("About Us", aboutUsIcon);
		btnAboutUs.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnAboutUs.setVerticalTextPosition(SwingConstants.CENTER);
		btnAboutUs.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		ImageIcon scaledaboutUsIcon = new ImageIcon(aboutUsIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
		btnAboutUs.setIcon(scaledaboutUsIcon);

		// Increase the space between the icon and text
		btnAboutUs.setMargin(new Insets(2, 10, 2, 10)); // Adjust the Insets as needed
		btnAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnAboutUs)
				{
					dispose();
					AboutUs aboutUsFrame = new AboutUs();
                    aboutUsFrame.setVisible(true);
				}
			}
		});
		btnAboutUs.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAboutUs.setBounds(240, 10, 113, 64);
		panel.add(btnAboutUs);	

		btnFind.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnDonate.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnAboutUs.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		logOut.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
		JLabel logo = new JLabel("");
		logo.setBounds(10, 10, 80, 64);
		panel.add(logo);
		logo.setForeground(new Color(255, 255, 255));
		logo.setBackground(new Color(255, 255, 255));
		ImageIcon img1 = new ImageIcon(this.getClass().getResource("/blood.png"));
		Image originalImage = img1.getImage();
		Image scaledImage = originalImage.getScaledInstance(80, 64, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		logo.setIcon(scaledIcon);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(new Color(128, 64, 0));
		tablePanel.setBounds(388, 118, 388, 412);
		contentPane.add(tablePanel);
		
		// Create a DefaultTableModel for the table
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Blood Type");
        tableModel.addColumn("Donate Blood To");
        tableModel.addColumn("Receive Blood From");

        // Sample data
        String[] rowData1 = {"A+", "A+", "AB+"};
        String[] rowData2 = {"A+", "A-", "O+"};
        String[] rowData3 = {"O+", "O+", "A+"};
        String[] rowData4 = {"B+", "B+", "AB+"};
        String[] rowData5 = {"AB+", "Everyone", ""};
        String[] rowData6 = {"A-", "A+", "AB+"};
        String[] rowData7 = {"O-", "Everyone", "O-"};
        String[] rowData8 = {"B-", "B+", "AB+"};
        String[] rowData9 = {"AB-", "AB+", "AB-"};

        // Add data to the table model
        tableModel.addRow(rowData1);
        tableModel.addRow(rowData2);
        tableModel.addRow(rowData3);
        tableModel.addRow(rowData4);
        tableModel.addRow(rowData5);
        tableModel.addRow(rowData6);
        tableModel.addRow(rowData7);
        tableModel.addRow(rowData8);
        tableModel.addRow(rowData9);

        // Set the preferred size of the tablePanel
        tablePanel.setPreferredSize(new Dimension(388, 449));
                        
        // Create the JTable using the table model
        JTable table = new JTable(tableModel);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12)); // Make column headers bold
                                
        table.setGridColor(Color.BLACK);
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(2, 2)); // Adjust the spacing to make the lines thicker
        
        // Add the table to a JScrollPane for scrolling
        table.setFillsViewportHeight(true);

        int cellHeight = 43; // You can change this value to the desired height
        table.setRowHeight(cellHeight);
        // Add the table to a JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane(table);

        // Use BorderLayout for tablePanel
        tablePanel.setLayout(new BorderLayout());

        // Add the scrollPane (containing the table) to the CENTER of the tablePanel
        tablePanel.add(scrollPane, BorderLayout.CENTER);
		
        JPanel quotePanel = new JPanel();
        quotePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        quotePanel.setBackground(new Color(255, 255, 255));
        quotePanel.setBounds(10, 118, 368, 412);
        contentPane.add(quotePanel);

        // Load and display the image on the quote panel
        try {
            ImageIcon quoteIcon = new ImageIcon(getClass().getResource("Quote.jpg")); // Update the path accordingly
            Image scaledQuote = quoteIcon.getImage().getScaledInstance(quotePanel.getWidth(), quotePanel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledQuoteIcon = new ImageIcon(scaledQuote);

            // Create a JLabel to display the scaled image
            JLabel quoteLabel = new JLabel(scaledQuoteIcon);
            quotePanel.add(quoteLabel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
	}

	protected void loginSrc() {
		// TODO Auto-generated method stub
		new Login();
		
	}
}
