package com;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class AboutUs extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AboutUs window = new AboutUs();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AboutUs() {
        setTitle("About Us");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 601);

        // Create the content pane with BorderLayout
        contentPane = new JPanel();
        setContentPane(contentPane);

        // Create a title label
        JLabel titleLabel = new JLabel("About Our Blood Donation System");
        titleLabel.setBounds(0, 0, 786, 42);
        titleLabel.setBackground(new Color(255, 128, 0));
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBackground(Color.GRAY);
        titleLabel.setOpaque(true);
        
        // Set the preferred size to increase the text height
        Dimension titleSize = titleLabel.getPreferredSize();
        titleSize.height = 60;
        contentPane.setLayout(null);
        titleLabel.setPreferredSize(titleSize);
        
        contentPane.add(titleLabel);

        // Create a scrollable text area with your content
        JEditorPane editorPane = new JEditorPane();
        editorPane.setFont(new Font("Tahoma", Font.BOLD, 20));
        editorPane.setContentType("text/html"); // Set content type to HTML
        editorPane.setEditable(false);
        editorPane.setText(
                "<html>"
                + "<center><h2><u><b>Welcome to our Blood Donation System</b></u></h2></center>"
                + "<ul>"
                + "<li><span style='font-size: 16px; font-family: Arial, sans-serif; color: red;'><b>Donor Registration:</b></span><span style='font-size: 13px; font-family: Arial, sans-serif';>We offer a seamless and user-friendly registration process for blood donors, making it convenient for individuals to join our mission.</span></li>"
                + "<br>"
                + "<li><span style='font-size: 16px; font-family: Arial, sans-serif; color: red;'><b>Blood Stock Management:</b></span><span style='font-size: 13px; font-family: Arial, sans-serif;'>Our system efficiently manages and tracks blood inventory, ensuring that hospitals and clinics have access to an adequate blood supply.</span></li>"
                + "<br>"
                + "<li><span style='font-size: 16px; font-family: Arial, sans-serif; color: red;'><b>Donor Outreach:</b></span><span style='font-size: 13px; font-family: Arial, sans-serif;'>We actively engage with donors and potential donors to inform them about upcoming donation drives, events, and the urgent need for blood.</span></li>"
                + "<br>"
                + "<li><span style='font-size: 16px; font-family: Arial, sans-serif; color: red;'><b>Educational Resources:</b></span><span style='font-size: 13px; font-family: Arial, sans-serif;'>Our platform provides educational materials and resources to raise awareness about the significance of blood donation and its impact on saving lives.</span></li>"
                + "<br>"
                + "<li><span style='font-size: 16px; font-family: Arial, sans-serif; color: red;'><b>Donation Tracking:</b></span><span style='font-size: 13px; font-family: Arial, sans-serif;'>Donors can easily track their donation history and receive updates on how their contributions have made a difference.</span></li>"
                + ""
                + "</ul>"
                + "<p style='margin-left: 20px;'><span style='font-size: 12px;'>We believe that every drop of blood donated has the potential to save a life."
                + "By using our Blood Donation System, you are becoming a part of this noble cause,"
                + "helping us ensure a steady blood supply for those in need.<br><br>"
                + "Thank you for being a lifesaver and making a positive impact on your community through blood donation."
                + "</span></p>"
                + "</html>"
            );
        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setBounds(0, 29, 786, 535);
        contentPane.add(scrollPane);
        
        JButton btnBack = new JButton("Back");
        btnBack.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource() == btnBack)
        		{
        			dispose();
        			Home launch = new Home();
        			launch.setVisible(true);
        		}
        	}
        });
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnBack.setBackground(new Color(255, 255, 255));
        btnBack.setBounds(45, 10, 85, 28);
        contentPane.add(btnBack);
        
    }
}
