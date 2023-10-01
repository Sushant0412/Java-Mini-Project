package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class Search extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField title;
    private JTextField txtEnterCity;
    private Choice cityChoice;
    private Choice bloodTypeChoice;
    private JTextField txtBloodType;

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

    public Search() {

        class AddressCellRenderer extends DefaultTableCellRenderer {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                if (column == 3) { // Replace 3 with the actual column index of the "Address" column
                    JTextArea textArea = new JTextArea();
                    textArea.setWrapStyleWord(true);
                    textArea.setLineWrap(true);
                    textArea.setFont(new Font("Tahoma", Font.PLAIN, 12)); // Adjust the font size and style as needed
                    textArea.setText((String) value);

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    // Set the preferred size for the JScrollPane to enable scrolling
                    scrollPane.setPreferredSize(new Dimension(200, 50)); // Adjust the width and height as needed

                    return scrollPane;
                } else {
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            }
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setResizable(false);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 1166, 543);
        panel.setBackground(new Color(255, 0, 0));
        panel.setForeground(new Color(255, 255, 255));
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(192, 192, 192));
        panel_1.setBounds(10, 136, 218, 39);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblSearchBlood = new JLabel("Search Blood Stock");
        lblSearchBlood.setHorizontalAlignment(SwingConstants.CENTER);
        lblSearchBlood.setBounds(0, 0, 218, 39);
        panel_1.add(lblSearchBlood);
        lblSearchBlood.setFont(new Font("Tahoma", Font.BOLD, 15));

        cityChoice = new Choice();
        cityChoice.setBounds(112, 199, 104, 18);
        cityChoice.add("Select City");
        cityChoice.add("Kurla");
        cityChoice.add("Chembur");
        cityChoice.add("Sion");
        cityChoice.add("Ghatkoper");
        cityChoice.add("Vashi");
        cityChoice.setForeground(Color.BLACK);
        panel.add(cityChoice);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Blood Type");
        tableModel.addColumn("Address");
        tableModel.addColumn("Contact");

        JTable jTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };

        TableColumn addressColumn = jTable.getColumnModel().getColumn(3); // Replace 3 with the actual column index of
                                                                          // the "Address" column
        addressColumn.setCellRenderer(new AddressCellRenderer());
        jTable.setFont(new Font("Tahoma", Font.BOLD, 15));
        addressColumn.setPreferredWidth(200); // Adjust the width as needed
        jTable.setRowHeight(60);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(252, 158, 904, 375);
        panel.add(scrollPane);

        txtEnterCity = new JTextField();
        txtEnterCity.setBounds(10, 199, 96, 19);
        txtEnterCity.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtEnterCity.setBorder(new CompoundBorder());
        txtEnterCity.setBackground(new Color(255, 0, 0));
        txtEnterCity.setText("Enter City:");
        panel.add(txtEnterCity);
        txtEnterCity.setColumns(10);
        /*
         * JTextArea stockOutput = new JTextArea();
         * stockOutput.setFont(new Font("Tahoma", Font.BOLD, 15));
         * stockOutput.
         * setText("                                            Available Donors: \r\n"
         * );
         * stockOutput.
         * append("\n        ID      |    Name     |   Blood Type   |    Address\n");
         * stockOutput.
         * append("    ----------------------------------------------------------\n");
         * 
         * // Create a JScrollPane and add the JTextArea to it
         * JScrollPane scrollPane = new JScrollPane(stockOutput);
         * scrollPane.setBounds(252, 158, 504, 345);
         * panel.add(scrollPane); // Add the JScrollPane to your panel
         */

        JButton btnNewButton = new JButton("Enter");
        btnNewButton.setBounds(70, 282, 85, 21);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedCity = cityChoice.getSelectedItem();
                    String selectedBloodType = bloodTypeChoice.getSelectedItem();

                    if ("Select City".equals(selectedCity) || "Select Type".equals(selectedBloodType)) {
                        JOptionPane.showMessageDialog(contentPane, "Please select both City and Blood Type.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        // Reset both input fields to their default values
                        cityChoice.select(0);
                        bloodTypeChoice.select(0);
                        return;
                    }

                    Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/donatebld", "root",
                            "root");

                    String query = "SELECT * FROM donorlist WHERE City = ? AND BloodType = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, selectedCity);
                    preparedStatement.setString(2, selectedBloodType);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    StringBuilder table = new StringBuilder();
                    table.append("\n        ID      |         Name          |   Blood Type   |    Address\n");
                    table.append("    ----------------------------------------------------------\n");

                    boolean foundEntries = false;

                    while (resultSet.next()) {
                        String address = resultSet.getString("Address");
                        String id = resultSet.getString("id");
                        String bloodType = resultSet.getString("BloodType");
                        String name = resultSet.getString("Name");
                        String contact = resultSet.getString("Contact");

                        // table.append(" ").append(id).append(" | ").append(name);
                        // table.append(" | ").append(bloodType).append(" |
                        // ").append(address).append("\n");

                        tableModel.addRow(new Object[] { id, name, bloodType, address, contact });

                        // table.append("\n");
                        foundEntries = true;

                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();

                    if (!foundEntries) {
                        // No entries found, display a pop-up message
                        JOptionPane.showMessageDialog(contentPane, "No entries found for the selected blood type.",
                                "No Entries Found", JOptionPane.INFORMATION_MESSAGE);
                        // stockOutput.setText("\t Available Donors: \n");
                        // stockOutput.append("\n ID | Name | Blood Type | Address\n");
                        // stockOutput.append("
                        // ----------------------------------------------------------\n");
                        cityChoice.select(0);
                        bloodTypeChoice.select(0);
                    } // else {
                      // Entries found, display the results in the JTextArea
                      // stockOutput.setText(" Available Donors: \r\n" + table.toString());
                      // }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(btnNewButton);

        title = new JTextField();
        title.setBounds(252, 10, 553, 62);
        panel.add(title);
        title.setFont(new Font("Tahoma", Font.BOLD, 15));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("Blood Stock Availability");
        title.setColumns(10);

        bloodTypeChoice = new Choice();
        bloodTypeChoice.setBounds(112, 243, 104, 18);
        bloodTypeChoice.add("Select Type");
        bloodTypeChoice.add("A+");
        bloodTypeChoice.add("B+");
        bloodTypeChoice.add("AB+");
        bloodTypeChoice.add("O+");
        bloodTypeChoice.add("A-");
        bloodTypeChoice.add("B-");
        bloodTypeChoice.add("AB-");
        bloodTypeChoice.add("O-");
        bloodTypeChoice.setForeground(Color.BLACK);
        panel.add(bloodTypeChoice);

        txtBloodType = new JTextField();
        txtBloodType.setBounds(10, 243, 96, 19);
        txtBloodType.setText("Blood Type:");
        txtBloodType.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtBloodType.setColumns(10);
        txtBloodType.setBorder(new CompoundBorder());
        txtBloodType.setBackground(Color.RED);
        panel.add(txtBloodType);

    }
}
