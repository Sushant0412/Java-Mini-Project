package com;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
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
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1011, 782);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnUser) {
					frame.dispose();
					Login start1 = new Login();
					start1.setVisible(true);
				}
			}
		});
		btnUser.setForeground(Color.RED);
		btnUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUser.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnUser.setBackground(Color.YELLOW);
		btnUser.setBounds(659, 550, 85, 28);
		frame.getContentPane().add(btnUser);
		
		JLabel adminBg = new JLabel(new ImageIcon("images/adminIcon.jpg"));
		frame.getContentPane().add(adminBg);
		adminBg.setBorder(new LineBorder(new Color(255, 0, 0), 2));
		adminBg.setBounds(187, 227, 230, 298);
		
		JLabel userBg = new JLabel(new ImageIcon("images/userIcon.png"));
		frame.getContentPane().add(userBg);
		userBg.setBorder(new LineBorder(new Color(0, 255, 0), 2));
		userBg.setBounds(582, 227, 239, 298);
		
		JLabel userLabel = new JLabel("User");
        userLabel.setBounds(582, 227, 239, 298);
        frame.getContentPane().add(userLabel);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnAdmin) {
					frame.dispose();
					Login start = new Login();
					start.setVisible(true);
				}
			}
		});
		btnAdmin.setForeground(new Color(255, 0, 0));
		btnAdmin.setBackground(new Color(255, 255, 0));
		btnAdmin.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnAdmin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdmin.setBounds(260, 550, 85, 28);
		frame.getContentPane().add(btnAdmin);			
		
		JLabel adminLabel = new JLabel("Admin");
		adminLabel.setBackground(new Color(0, 0, 0));
		adminLabel.setBounds(187, 227, 230, 298);
		frame.getContentPane().add(adminLabel);
		
		JLabel Title = new JLabel("Welcome to BloodBank Management System");
		Title.setForeground(new Color(255, 255, 255));
		Title.setFont(new Font("Tahoma", Font.BOLD, 20));
		Title.setHorizontalTextPosition(SwingConstants.CENTER);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(252, 60, 492, 65);
		frame.getContentPane().add(Title);
		
		JLabel background = new JLabel(new ImageIcon("images/welcomeBg.jpg"));
        background.setBounds(0, 0, 997, 745);
        frame.getContentPane().add(background);
        
	}
}
