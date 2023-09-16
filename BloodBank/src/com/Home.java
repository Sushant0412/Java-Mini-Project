package com;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 0));
		panel.setBorder(new EmptyBorder(0, 3, 0, 0));
		panel.setBounds(10, 10, 766, 84);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnFind = new JButton("Looking For Blood");
		btnFind.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnFind.setBounds(363, 10, 132, 64);
		panel.add(btnFind);
		
		JButton btnDonate = new JButton("Donate Blood");
		btnDonate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDonate.setBounds(505, 10, 113, 64);
		panel.add(btnDonate);
		
		JButton logOut = new JButton("Logout");
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
		
		JButton btnAboutUs = new JButton("About Us");
		btnAboutUs.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAboutUs.setBounds(240, 10, 113, 64);
		panel.add(btnAboutUs);
		
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
	}

	protected void loginSrc() {
		// TODO Auto-generated method stub
		new Login();
		
	}
}