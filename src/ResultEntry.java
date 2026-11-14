
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultEntry extends JFrame {

    // Colours
    Color blue = new Color(37, 42, 114);
    Color darkText = new Color(35, 35, 45);
    Color borderColor = new Color(215, 220, 232);
    Color lightBackground = new Color(248, 249, 252);

    private JTextField idField;
    private JTextField nameField;
    private JComboBox<String> branchBox;
    private JComboBox<String> sectionBox;
    private JComboBox<String> semesterBox;

    private JTextField marks1;
    private JTextField marks2;
    private JTextField marks3;
    private JTextField marks4;
    private JTextField marks5;
    private JTextField marks6;

    public ResultEntry() {

        setTitle("Enter Result - Result Management System");
        setSize(900, 700);
        setMinimumSize(new Dimension(900, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());

        // Header
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

        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(lightBackground);
        resultPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        resultPanel.setLayout(null);
        resultPanel.setPreferredSize(new Dimension(740, 510));
        resultPanel.setMinimumSize(new Dimension(740, 510));
        resultPanel.setMaximumSize(new Dimension(740, 510));

        // Title
        JLabel title = new JLabel("ENTER STUDENT RESULT");
        title.setFont(new Font("Arial", Font.BOLD, 27));
        title.setForeground(blue);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(150, 15, 440, 40);
        resultPanel.add(title);

        JPanel line = new JPanel();
        line.setBackground(blue);
        line.setBounds(320, 60, 100, 3);
        resultPanel.add(line);

        // Student ID
        JLabel idLabel = createLabel("Student ID");
        idLabel.setBounds(40, 85, 110, 30);
        resultPanel.add(idLabel);

        idField = createTextField();
        idField.setBounds(155, 80, 200, 38);
        resultPanel.add(idField);

        // Student Name
        JLabel nameLabel = createLabel("Student Name");
        nameLabel.setBounds(390, 85, 120, 30);
        resultPanel.add(nameLabel);

        nameField = createTextField();
        nameField.setBounds(515, 80, 180, 38);
        resultPanel.add(nameField);

        // Branch
        JLabel branchLabel = createLabel("Branch");
        branchLabel.setBounds(40, 135, 110, 30);
        resultPanel.add(branchLabel);

        String[] branches = {
                "Select Branch",
                "CSE Core",
                "CSE with AI",
                "CSE with IoT",
                "CSE Regional",
                "AI & DS",
                "CS & IT"
        };

        branchBox = new JComboBox<>(branches);
        branchBox.setBounds(155, 130, 200, 38);
        branchBox.setFont(new Font("Arial", Font.PLAIN, 13));
        resultPanel.add(branchBox);

        // Section
        JLabel sectionLabel = createLabel("Section");
        sectionLabel.setBounds(390, 135, 120, 30);
        resultPanel.add(sectionLabel);

        String[] sections = {"Select", "A", "B", "C", "D"};

        sectionBox = new JComboBox<>(sections);
        sectionBox.setBounds(515, 130, 180, 38);
        sectionBox.setFont(new Font("Arial", Font.PLAIN, 13));
        resultPanel.add(sectionBox);

        // Semester
        JLabel semesterLabel = createLabel("Semester");
        semesterLabel.setBounds(40, 185, 110, 30);
        resultPanel.add(semesterLabel);

        String[] semesters = {
                "Select Semester",
                "1st", "2nd", "3rd", "4th",
                "5th", "6th", "7th", "8th"
        };

        semesterBox = new JComboBox<>(semesters);
        semesterBox.setBounds(155, 180, 200, 38);
        semesterBox.setFont(new Font("Arial", Font.PLAIN, 13));
        resultPanel.add(semesterBox);

        // Subject marks title
        JLabel marksTitle = new JLabel("Subject Marks");
        marksTitle.setFont(new Font("Arial", Font.BOLD, 16));
        marksTitle.setForeground(blue);
        marksTitle.setBounds(40, 230, 200, 30);
        resultPanel.add(marksTitle);

        // Subject 1
        JLabel subject1 = createLabel("Subject 1");
        subject1.setBounds(40, 270, 100, 30);
        resultPanel.add(subject1);

        marks1 = createTextField();
        marks1.setBounds(135, 265, 80, 35);
        resultPanel.add(marks1);

        // Subject 2
        JLabel subject2 = createLabel("Subject 2");
        subject2.setBounds(235, 270, 100, 30);
        resultPanel.add(subject2);

        marks2 = createTextField();
        marks2.setBounds(330, 265, 80, 35);
        resultPanel.add(marks2);

        // Subject 3
        JLabel subject3 = createLabel("Subject 3");
        subject3.setBounds(430, 270, 100, 30);
        resultPanel.add(subject3);

        marks3 = createTextField();
        marks3.setBounds(525, 265, 80, 35);
        resultPanel.add(marks3);

        // Subject 4
        JLabel subject4 = createLabel("Subject 4");
        subject4.setBounds(40, 320, 100, 30);
        resultPanel.add(subject4);

        marks4 = createTextField();
        marks4.setBounds(135, 315, 80, 35);
        resultPanel.add(marks4);

        // Subject 5
        JLabel subject5 = createLabel("Subject 5");
        subject5.setBounds(235, 320, 100, 30);
        resultPanel.add(subject5);

        marks5 = createTextField();
        marks5.setBounds(330, 315, 80, 35);
        resultPanel.add(marks5);

        // Subject 6
        JLabel subject6 = createLabel("Subject 6");
        subject6.setBounds(430, 320, 100, 30);
        resultPanel.add(subject6);

        marks6 = createTextField();
        marks6.setBounds(525, 315, 80, 35);
        resultPanel.add(marks6);

        // Save button
        JButton saveButton = new JButton("SAVE RESULT");
        saveButton.setBounds(155, 380, 200, 42);
        saveButton.setBackground(blue);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder());
        resultPanel.add(saveButton);

        // Clear button
        JButton clearButton = new JButton("CLEAR");
        clearButton.setBounds(390, 380, 200, 42);
        clearButton.setBackground(Color.WHITE);
        clearButton.setForeground(blue);
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setFocusPainted(false);
        clearButton.setBorder(BorderFactory.createLineBorder(borderColor));
        resultPanel.add(clearButton);

        // Back button
        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setBounds(260, 440, 260, 35);
        backButton.setForeground(blue);
        backButton.setFont(new Font("Arial", Font.BOLD, 13));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        resultPanel.add(backButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        mainPanel.add(resultPanel, gbc);
        add(mainPanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(blue);
        footer.setPreferredSize(new Dimension(0, 38));

        JLabel footerText = new JLabel(
                "Result Management System | Developed by Khyati Pathak | B1 Batch");
        footerText.setForeground(Color.WHITE);
        footerText.setFont(new Font("Arial", Font.PLAIN, 14));
        footer.add(footerText);

        add(footer, BorderLayout.SOUTH);

        // Save result to MySQL
        saveButton.addActionListener(e -> saveResult());

        // Clear form
        clearButton.addActionListener(e -> clearForm());

        // Back to dashboard
        backButton.addActionListener(e -> {
            new Dashboard().setVisible(true);
            dispose();
        });
    }

    private void saveResult() {

        String id = idField.getText().trim();
        String name = nameField.getText().trim();

        String branch = (String) branchBox.getSelectedItem();
        String section = (String) sectionBox.getSelectedItem();
        String semester = (String) semesterBox.getSelectedItem();

        // Validate student details
        if (id.isEmpty() || name.isEmpty()
                || branchBox.getSelectedIndex() == 0
                || sectionBox.getSelectedIndex() == 0
                || semesterBox.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all student details.",
                    "Incomplete Details",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Student Name should contain only alphabets and spaces.",
                    "Invalid Name",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int[] marks = new int[6];

        JTextField[] fields = {
                marks1, marks2, marks3,
                marks4, marks5, marks6
        };

        // Read and validate marks
        try {
            for (int i = 0; i < fields.length; i++) {
                String value = fields[i].getText().trim();

                if (value.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter marks for all six subjects.",
                            "Missing Marks",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                marks[i] = Integer.parseInt(value);

                if (marks[i] < 0 || marks[i] > 100) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Marks must be between 0 and 100.",
                            "Invalid Marks",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Enter whole numbers only for subject marks.",
                    "Invalid Marks",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int total = 0;
        boolean passed = true;

        for (int mark : marks) {
            total += mark;

            if (mark < 40) {
                passed = false;
            }
        }

        double percentage = total / 6.0;
        String status = passed ? "PASS" : "FAIL";

        // Match the semester format used by StudentManagement.
        String registeredSemester = semester + " Semester";

        String selectSQL =
                "SELECT student_name, branch, section, semester " +
                        "FROM students WHERE student_id = ?";

        String insertSQL =
                "INSERT INTO results " +
                        "(student_id, semester, subject1, subject2, subject3, " +
                        "subject4, subject5, subject6, total, percentage, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {

            // Check that the student exists and details match.
            try (PreparedStatement check = con.prepareStatement(selectSQL)) {

                check.setString(1, id);

                try (ResultSet rs = check.executeQuery()) {

                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Student ID not found. Register the student first.",
                                "Student Not Found",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String dbName = rs.getString("student_name");
                    String dbBranch = rs.getString("branch");
                    String dbSection = rs.getString("section");
                    String dbSemester = rs.getString("semester");

                    if (!dbName.equalsIgnoreCase(name)
                            || !dbBranch.equals(branch)
                            || !dbSection.equals(section)
                            || !dbSemester.equalsIgnoreCase(registeredSemester)) {

                        JOptionPane.showMessageDialog(
                                this,
                                "The entered details do not match the registered student.\n\n"
                                        + "Registered name: " + dbName
                                        + "\nBranch: " + dbBranch
                                        + "\nSection: " + dbSection
                                        + "\nSemester: " + dbSemester,
                                "Student Details Mismatch",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }

            // Insert the result.
            try (PreparedStatement ps = con.prepareStatement(insertSQL)) {

                ps.setString(1, id);
                ps.setString(2, semester);

                for (int i = 0; i < marks.length; i++) {
                    ps.setInt(i + 3, marks[i]);
                }

                ps.setInt(9, total);
                ps.setDouble(10, percentage);
                ps.setString(11, status);

                int rowsInserted = ps.executeUpdate();

                if (rowsInserted == 1) {
                    StringBuilder message = new StringBuilder();

                    message.append("Result saved successfully!\n\n");
                    message.append("Student: ").append(name);
                    message.append("\nStudent ID: ").append(id);
                    message.append("\nSemester: ").append(semester);
                    message.append("\n\nSubject-wise Result:\n");

                    for (int i = 0; i < marks.length; i++) {
                        message.append("Subject ").append(i + 1)
                                .append(": ").append(marks[i])
                                .append(" - ")
                                .append(marks[i] >= 40 ? "PASS" : "FAIL")
                                .append("\n");
                    }

                    message.append("\nTotal Marks: ")
                            .append(total).append(" / 600");

                    message.append("\nPercentage: ")
                            .append(String.format("%.2f", percentage))
                            .append("%");

                    message.append("\nOverall Result: ").append(status);

                    JOptionPane.showMessageDialog(
                            this,
                            message.toString(),
                            "Result Saved",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (SQLException ex) {

            // MySQL error 1062 = duplicate key.
            if (ex.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(
                        this,
                        "A result already exists for this student and semester.",
                        "Duplicate Result",
                        JOptionPane.WARNING_MESSAGE);
            } else if ("23000".equals(ex.getSQLState())) {
                JOptionPane.showMessageDialog(
                        this,
                        "Database constraint error. Check the student ID and result table.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Could not save the result.\n" + ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        branchBox.setSelectedIndex(0);
        sectionBox.setSelectedIndex(0);
        semesterBox.setSelectedIndex(0);

        marks1.setText("");
        marks2.setText("");
        marks3.setText("");
        marks4.setText("");
        marks5.setText("");
        marks6.setText("");
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(darkText);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 14));

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));

        return field;
    }

    // Run this screen independently for testing.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResultEntry().setVisible(true));
    }
}
