
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentManagement extends JFrame {

    // Colours
    Color blue = new Color(37, 42, 114);
    Color darkText = new Color(35, 35, 45);
    Color borderColor = new Color(215, 220, 232);
    Color lightBackground = new Color(248, 249, 252);

    public StudentManagement() {

        setTitle("Student Details - Result Management System");
        setSize(900, 700);
        setMinimumSize(new Dimension(900, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());

        // HEADER
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        java.net.URL logoURL = getClass().getResource("/login.png");

        if (logoURL != null) {
            ImageIcon logo = new ImageIcon(logoURL);
            Image resizedImage = logo.getImage().getScaledInstance(
                    850, 150, Image.SCALE_SMOOTH);

            JLabel header = new JLabel(new ImageIcon(resizedImage));
            header.setHorizontalAlignment(JLabel.CENTER);
            header.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            headerPanel.add(header, BorderLayout.CENTER);
        }

        add(headerPanel, BorderLayout.NORTH);

        // MAIN AREA
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        // FORM CONTAINER
        JPanel formPanel = new JPanel();
        formPanel.setBackground(lightBackground);
        formPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(660, 500));
        formPanel.setMinimumSize(new Dimension(660, 500));
        formPanel.setMaximumSize(new Dimension(660, 500));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(formPanel, gbc);

        // TITLE
        JLabel title = new JLabel("STUDENT DETAILS");
        title.setFont(new Font("Arial", Font.BOLD, 27));
        title.setForeground(blue);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(150, 20, 360, 40);
        formPanel.add(title);

        // TITLE LINE
        JPanel line = new JPanel();
        line.setBackground(blue);
        line.setBounds(280, 65, 100, 3);
        formPanel.add(line);

        // STUDENT NAME
        JLabel nameLabel = createLabel("Student Name");
        nameLabel.setBounds(55, 100, 130, 30);
        formPanel.add(nameLabel);

        JTextField nameField = createTextField();
        nameField.setBounds(200, 95, 370, 40);
        formPanel.add(nameField);

        // ROLL NUMBER
        JLabel rollLabel = createLabel("Roll Number");
        rollLabel.setBounds(55, 150, 130, 30);
        formPanel.add(rollLabel);

        JTextField rollField = createTextField();
        rollField.setBounds(200, 145, 370, 40);
        formPanel.add(rollField);

        // BRANCH
        JLabel branchLabel = createLabel("Branch");
        branchLabel.setBounds(55, 200, 130, 30);
        formPanel.add(branchLabel);

        String[] branches = {
                "Select Branch",
                "CSE Core",
                "CSE with AI",
                "CSE with IoT",
                "CSE Regional",
                "AI & DS",
                "CS & IT"
        };

        JComboBox<String> branchBox = new JComboBox<>(branches);
        branchBox.setBounds(200, 195, 370, 40);
        branchBox.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(branchBox);

        // SECTION
        JLabel sectionLabel = createLabel("Section");
        sectionLabel.setBounds(55, 250, 130, 30);
        formPanel.add(sectionLabel);

        String[] sections = {
                "Select Section", "A", "B", "C", "D"
        };

        JComboBox<String> sectionBox = new JComboBox<>(sections);
        sectionBox.setBounds(200, 245, 170, 40);
        sectionBox.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(sectionBox);

        // SEMESTER
        JLabel semesterLabel = createLabel("Semester");
        semesterLabel.setBounds(390, 250, 80, 30);
        formPanel.add(semesterLabel);

        String[] semesters = {
                "Select Semester",
                "1st Semester",
                "2nd Semester",
                "3rd Semester",
                "4th Semester",
                "5th Semester",
                "6th Semester",
                "7th Semester",
                "8th Semester"
        };

        JComboBox<String> semesterBox = new JComboBox<>(semesters);
        semesterBox.setBounds(475, 245, 95, 40);
        semesterBox.setFont(new Font("Arial", Font.PLAIN, 12));
        formPanel.add(semesterBox);

        // PHONE NUMBER
        JLabel phoneLabel = createLabel("Phone Number");
        phoneLabel.setBounds(55, 300, 130, 30);
        formPanel.add(phoneLabel);

        JTextField phoneField = createTextField();
        phoneField.setBounds(200, 295, 370, 40);
        formPanel.add(phoneField);

        // SAVE BUTTON
        JButton saveButton = new JButton("SAVE STUDENT");
        saveButton.setBounds(200, 365, 175, 45);
        saveButton.setBackground(blue);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder());
        formPanel.add(saveButton);

        // CLEAR BUTTON
        JButton clearButton = new JButton("CLEAR");
        clearButton.setBounds(395, 365, 175, 45);
        clearButton.setForeground(blue);
        clearButton.setBackground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setFocusPainted(false);
        clearButton.setBorder(
                BorderFactory.createLineBorder(borderColor));
        formPanel.add(clearButton);

        // BACK BUTTON
        JButton backButton = new JButton("← BACK TO DASHBOARD");
        backButton.setBounds(230, 425, 200, 40);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(blue);
        backButton.setFont(new Font("Arial", Font.BOLD, 13));
        backButton.setFocusPainted(false);
        backButton.setBorder(
                BorderFactory.createLineBorder(borderColor, 1));
        formPanel.add(backButton);

        add(mainPanel, BorderLayout.CENTER);

        // FOOTER
        JPanel footer = new JPanel();
        footer.setBackground(blue);
        footer.setPreferredSize(new Dimension(0, 38));

        JLabel footerText = new JLabel(
                "Result Management System | Developed by Khyati Pathak | B1 Batch");
        footerText.setForeground(Color.WHITE);
        footerText.setFont(new Font("Arial", Font.PLAIN, 14));
        footer.add(footerText);

        add(footer, BorderLayout.SOUTH);

        // SAVE STUDENT TO MYSQL
        saveButton.addActionListener(e -> {

            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            String branch = (String) branchBox.getSelectedItem();
            String section = (String) sectionBox.getSelectedItem();
            String semester = (String) semesterBox.getSelectedItem();
            String phone = phoneField.getText().trim();

            // Validate empty fields
            if (name.isEmpty() || roll.isEmpty() || phone.isEmpty()
                    || "Select Branch".equals(branch)
                    || "Select Section".equals(section)
                    || "Select Semester".equals(semester)) {

                JOptionPane.showMessageDialog(this,
                        "Please fill all student details.",
                        "Incomplete Details",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validate name
            if (!name.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(this,
                        "Student name should contain only letters.",
                        "Invalid Student Name",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validate phone
            if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid 10-digit phone number.",
                        "Invalid Phone Number",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "INSERT INTO students "
                    + "(student_id, student_name, branch, section, semester, phone) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, roll);
                pst.setString(2, name);
                pst.setString(3, branch);
                pst.setString(4, section);
                pst.setString(5, semester);
                pst.setString(6, phone);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Student details saved successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear fields after successful save
                nameField.setText("");
                rollField.setText("");
                phoneField.setText("");
                branchBox.setSelectedIndex(0);
                sectionBox.setSelectedIndex(0);
                semesterBox.setSelectedIndex(0);

            } catch (SQLException ex) {

                if ("23000".equals(ex.getSQLState())) {
                    JOptionPane.showMessageDialog(this,
                            "This roll number already exists. "
                                    + "Please use a unique roll number.",
                            "Duplicate Student",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Could not save student details.\n"
                                    + ex.getMessage(),
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                ex.printStackTrace();
            }
        });

        // CLEAR FIELDS
        clearButton.addActionListener(e -> {
            nameField.setText("");
            rollField.setText("");
            phoneField.setText("");
            branchBox.setSelectedIndex(0);
            sectionBox.setSelectedIndex(0);
            semesterBox.setSelectedIndex(0);
        });

        // BACK TO DASHBOARD
        backButton.addActionListener(e -> {
            new Dashboard().setVisible(true);
            dispose();
        });
    }

    // LABEL CREATOR
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(darkText);
        return label;
    }

    // TEXT FIELD CREATOR
    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(borderColor),
                        BorderFactory.createEmptyBorder(5, 8, 5, 8)
                )
        );
        return field;
    }

    // RUN THIS PAGE INDIVIDUALLY
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new StudentManagement().setVisible(true));
    }
}
