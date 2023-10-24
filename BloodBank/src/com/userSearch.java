package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.border.*;
import javax.swing.table.*;

public class userSearch extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField title;
    private JTextField txtEnterCity;
    private JComboBox<String> cityChoice; // Use AutoCompleteComboBox for cityChoice
    private JComboBox<String> bloodTypeChoice; // Use AutoCompleteComboBox for bloodTypeChoice
    private JTextField txtBloodType;

    private void setupAutoComplete(JComboBox<String> comboBox, String[] options) {
        JTextField textField = (JTextField) comboBox.getEditor().getEditorComponent();
        ArrayList<String> itemList = new ArrayList<>();
        for (String option : options) {
            itemList.add(option);
        }

        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(() -> {
                    String text = textField.getText();
                    autoComplete(comboBox, text, itemList);
                });
            }

			private void autoComplete(JComboBox<String> comboBox, String text, ArrayList<String> itemList) {
				// TODO Auto-generated method stub
				String enteredText = text;
				
				ArrayList<String> filteredItems = (ArrayList<String>) itemList.stream()
			            .filter(item -> Pattern.compile(Pattern.quote(enteredText), Pattern.CASE_INSENSITIVE).matcher(item).find())
			            .collect(Collectors.toList());

			    if (!filteredItems.isEmpty()) {
			        comboBox.setModel(new DefaultComboBoxModel<>(filteredItems.toArray(new String[0])));
			        comboBox.setSelectedItem(enteredText);
			        comboBox.showPopup();
			    } else {
			        comboBox.hidePopup();
			    }
				
			}
        });
    }

    private void autoComplete(JComboBox<String> comboBox, String enteredText, ArrayList<String> itemList) {
        ArrayList<String> filteredItems = (ArrayList<String>) itemList.stream()
                .filter(item -> Pattern.compile(Pattern.quote(enteredText), Pattern.CASE_INSENSITIVE).matcher(item).find())
                .collect(Collectors.toList());

        if (!filteredItems.isEmpty()) {
            comboBox.setModel(new DefaultComboBoxModel<>(filteredItems.toArray(new String[0])));
            comboBox.setSelectedItem(enteredText);
            comboBox.showPopup();
        } else {
            comboBox.hidePopup();
        }
    }

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

    public userSearch() {
        class AddressCellRenderer extends DefaultTableCellRenderer {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                if (column == 3) {
                    JTextArea textArea = new JTextArea();
                    textArea.setWrapStyleWord(true);
                    textArea.setLineWrap(true);
                    textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
                    textArea.setText((String) value);

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    scrollPane.setPreferredSize(new Dimension(200, 50));

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

        JPanel searchHeading = new JPanel();
        searchHeading.setBackground(new Color(192, 192, 192));
        searchHeading.setBounds(10, 158, 260, 39);
        panel.add(searchHeading);
        searchHeading.setLayout(null);

        JLabel lblSearchBlood = new JLabel("Search Blood Stock");
        lblSearchBlood.setBounds(0, 0, 265, 39);
        searchHeading.add(lblSearchBlood);
        lblSearchBlood.setHorizontalAlignment(SwingConstants.CENTER);
        lblSearchBlood.setFont(new Font("Tahoma", Font.BOLD, 15));

        String[] cityOptions = {"", "Kurla", "Chembur", "Sion", "Thane", "Kalyan", "Dadar", "Vashi"};
        cityChoice = new JComboBox<>(cityOptions);
        cityChoice.setEditable(true); // Allow user input
        setupAutoComplete(cityChoice, cityOptions);
        cityChoice.setBounds(111, 227, 159, 18);
        cityChoice.setForeground(Color.BLACK);
        panel.add(cityChoice);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Blood Type");
        tableModel.addColumn("Address");
        tableModel.addColumn("Contact");
        tableModel.addColumn("Age");

        JTable jTable = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        TableColumn addressColumn = jTable.getColumnModel().getColumn(3);
        addressColumn.setCellRenderer(new AddressCellRenderer());

        jTable.setFont(new Font("Tahoma", Font.BOLD, 15));
        addressColumn.setPreferredWidth(200);
        jTable.setRowHeight(60);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(288, 158, 868, 375);
        panel.add(scrollPane);

        txtEnterCity = new JTextField();
        txtEnterCity.setBounds(10, 226, 96, 19);
        txtEnterCity.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtEnterCity.setBorder(new CompoundBorder());
        txtEnterCity.setBackground(new Color(255, 0, 0));
        txtEnterCity.setText("Enter City:");
        panel.add(txtEnterCity);
        txtEnterCity.setColumns(10);

        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(71, 310, 85, 21);
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedCity = (String) cityChoice.getSelectedItem();
                    String selectedBloodType = (String) bloodTypeChoice.getSelectedItem();

                    if ("Select City".equals(selectedCity) || "Select Type".equals(selectedBloodType)) {
                        JOptionPane.showMessageDialog(contentPane, "Please select both City and Blood Type.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        cityChoice.setSelectedIndex(0);
                        bloodTypeChoice.setSelectedIndex(0);
                        return;
                    }

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3310/bloodbank?useSSL=false&allowPublicKeyRetrieval=true", "root",
                            "root");

                    String query = "SELECT *, TIMESTAMPDIFF(YEAR, DOB, CURDATE()) AS Age FROM donor WHERE City = ? AND blood_Type = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, selectedCity);
                    preparedStatement.setString(2, selectedBloodType);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    boolean foundEntries = false;

                    tableModel.setRowCount(0);

                    while (resultSet.next()) {
                        String address = resultSet.getString("Address");
                        String id = resultSet.getString("donor_id");
                        String bloodType = resultSet.getString("blood_Type");
                        String name = resultSet.getString("donor_name");
                        String contact = resultSet.getString("dr_contact");
                        int age = resultSet.getInt("Age");

                        tableModel.addRow(new Object[]{id, name, bloodType, address, contact, age});
                        foundEntries = true;
                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();

                    if (!foundEntries) {
                        JOptionPane.showMessageDialog(contentPane, "No entries found for the selected blood type.",
                                "No Entries Found", JOptionPane.INFORMATION_MESSAGE);
                        cityChoice.setSelectedIndex(0);
                        bloodTypeChoice.setSelectedIndex(0);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        enterButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(enterButton);

        title = new JTextField();
        title.setBackground(new Color(192, 192, 192));
        title.setBounds(288, 37, 868, 62);
        panel.add(title);
        title.setFont(new Font("Tahoma", Font.BOLD, 15));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("Blood Stock Availability");
        title.setColumns(10);

        String[] bloodTypeOptions = {"", "A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-"};
        bloodTypeChoice = new JComboBox<>(bloodTypeOptions);
        bloodTypeChoice.setEditable(true);
        setupAutoComplete(bloodTypeChoice, bloodTypeOptions);
        bloodTypeChoice.setBounds(111, 269, 159, 18);
        bloodTypeChoice.setForeground(Color.BLACK);
        panel.add(bloodTypeChoice);

        txtBloodType = new JTextField();
        txtBloodType.setBounds(10, 269, 96, 19);
        txtBloodType.setText("Blood Type:");
        txtBloodType.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtBloodType.setColumns(10);
        txtBloodType.setBorder(new CompoundBorder());
        txtBloodType.setBackground(Color.RED);
        panel.add(txtBloodType);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton) {
                    dispose();
                    userHome launch = new userHome();
                    launch.setVisible(true);
                }
            }
        });
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setBounds(58, 47, 98, 40);
        panel.add(backButton);
    }
}
