
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reports extends JFrame {

    Color blue = new Color(37, 42, 114);
    Color darkText = new Color(35, 35, 45);
    Color borderColor = new Color(215, 220, 232);
    Color lightBackground = new Color(248, 249, 252);

    private JTextField idField;
    private JTextField nameField;
    private JComboBox<String> branchBox;
    private JComboBox<String> sectionBox;
    private JComboBox<String> semesterBox;

    public Reports() {
        setTitle("Student Reports - Result Management System");
        setSize(900, 700);
        setMinimumSize(new Dimension(900, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel();
        header.setHorizontalAlignment(JLabel.CENTER);

        java.net.URL logoURL = getClass().getResource("/login.png");
        if (logoURL != null) {
            ImageIcon logo = new ImageIcon(logoURL);
            Image resizedImage = logo.getImage().getScaledInstance(
                    850, 150, Image.SCALE_SMOOTH);
            header.setIcon(new ImageIcon(resizedImage));
        }

        header.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 8));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(header, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel reportPanel = new JPanel(new GridBagLayout());
        reportPanel.setBackground(lightBackground);
        reportPanel.setBorder(
                BorderFactory.createLineBorder(borderColor, 1));
        reportPanel.setPreferredSize(new Dimension(660, 500));

        // Title
        JLabel title = new JLabel(
                "STUDENT REPORTS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 27));
        title.setForeground(blue);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        gbc.insets = new Insets(18, 40, 5, 40);
        reportPanel.add(title, gbc);

        // Decorative line
        JPanel line = new JPanel();
        line.setBackground(blue);
        line.setPreferredSize(new Dimension(100, 3));

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 15, 0);
        reportPanel.add(line, gbc);

        // Student ID
        JLabel idLabel = createLabel("Student ID");
        idField = createTextField();
        addFormRow(reportPanel, idLabel, idField, 2);

        // Student Name
        JLabel nameLabel = createLabel("Student Name");
        nameField = createTextField();
        addFormRow(reportPanel, nameLabel, nameField, 3);

        // Branch
        JLabel branchLabel = createLabel("Branch");
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
        branchBox.setFont(new Font("Arial", Font.PLAIN, 14));
        addFormRow(reportPanel, branchLabel, branchBox, 4);

        // Section
        JLabel sectionLabel = createLabel("Section");
        String[] sections = {
                "Select Section", "A", "B", "C", "D"
        };
        sectionBox = new JComboBox<>(sections);
        sectionBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Semester
        JLabel semesterLabel = createLabel("Semester");
        String[] semesters = {
                "Select", "1st", "2nd", "3rd", "4th",
                "5th", "6th", "7th", "8th"
        };
        semesterBox = new JComboBox<>(semesters);
        semesterBox.setFont(new Font("Arial", Font.PLAIN, 12));

        // Section and semester row
        GridBagConstraints rowGbc = new GridBagConstraints();
        rowGbc.fill = GridBagConstraints.HORIZONTAL;
        rowGbc.insets = new Insets(7, 40, 7, 10);
        rowGbc.gridx = 0;
        rowGbc.gridy = 5;
        rowGbc.weightx = 0;
        reportPanel.add(sectionLabel, rowGbc);

        rowGbc.gridx = 1;
        rowGbc.weightx = 1;
        reportPanel.add(sectionBox, rowGbc);

        GridBagConstraints semesterLabelGbc =
                new GridBagConstraints();
        semesterLabelGbc.fill = GridBagConstraints.HORIZONTAL;
        semesterLabelGbc.insets = new Insets(7, 15, 7, 10);
        semesterLabelGbc.gridx = 2;
        semesterLabelGbc.gridy = 5;
        semesterLabelGbc.weightx = 0;
        reportPanel.add(semesterLabel, semesterLabelGbc);

        GridBagConstraints semesterBoxGbc =
                new GridBagConstraints();
        semesterBoxGbc.fill = GridBagConstraints.HORIZONTAL;
        semesterBoxGbc.insets = new Insets(7, 0, 7, 40);
        semesterBoxGbc.gridx = 3;
        semesterBoxGbc.gridy = 5;
        semesterBoxGbc.weightx = 0.5;
        reportPanel.add(semesterBox, semesterBoxGbc);

        // Generate Report button
        JButton generateButton = new JButton("GENERATE REPORT");
        generateButton.setBackground(blue);
        generateButton.setForeground(Color.WHITE);
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateButton.setFocusPainted(false);
        generateButton.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints generateGbc = new GridBagConstraints();
        generateGbc.gridx = 0;
        generateGbc.gridy = 6;
        generateGbc.gridwidth = 4;
        generateGbc.fill = GridBagConstraints.HORIZONTAL;
        generateGbc.insets = new Insets(25, 40, 8, 40);
        reportPanel.add(generateButton, generateGbc);

        // Clear button
        JButton clearButton = new JButton("CLEAR");
        clearButton.setBackground(Color.WHITE);
        clearButton.setForeground(blue);
        clearButton.setFont(new Font("Arial", Font.BOLD, 13));
        clearButton.setFocusPainted(false);
        clearButton.setBorder(
                BorderFactory.createLineBorder(borderColor));

        GridBagConstraints clearGbc = new GridBagConstraints();
        clearGbc.gridx = 0;
        clearGbc.gridy = 7;
        clearGbc.gridwidth = 2;
        clearGbc.fill = GridBagConstraints.HORIZONTAL;
        clearGbc.insets = new Insets(5, 40, 20, 8);
        reportPanel.add(clearButton, clearGbc);

        // Back button
        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setForeground(blue);
        backButton.setFont(new Font("Arial", Font.BOLD, 13));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);

        GridBagConstraints backGbc = new GridBagConstraints();
        backGbc.gridx = 2;
        backGbc.gridy = 7;
        backGbc.gridwidth = 2;
        backGbc.fill = GridBagConstraints.HORIZONTAL;
        backGbc.insets = new Insets(5, 8, 20, 40);
        reportPanel.add(backButton, backGbc);

        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.anchor = GridBagConstraints.CENTER;
        mainGbc.weightx = 1;
        mainGbc.weighty = 1;
        mainPanel.add(reportPanel, mainGbc);

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

        // Generate report action
        generateButton.addActionListener(e -> generateReport());

        // Clear action
        clearButton.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            branchBox.setSelectedIndex(0);
            sectionBox.setSelectedIndex(0);
            semesterBox.setSelectedIndex(0);
        });

        // Back action
        backButton.addActionListener(e -> {
            new Dashboard().setVisible(true);
            dispose();
        });
    }

    private void generateReport() {
        String studentId = idField.getText().trim();
        String studentName = nameField.getText().trim();
        String branch = branchBox.getSelectedItem().toString();
        String section = sectionBox.getSelectedItem().toString();
        String semester = semesterBox.getSelectedItem().toString();

        if (studentId.isEmpty() || studentName.isEmpty()
                || branch.equals("Select Branch")
                || section.equals("Select Section")
                || semester.equals("Select")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter all student details.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!studentName.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Student Name should contain only letters and spaces.",
                    "Invalid Student Name",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql =
                "SELECT s.student_id, s.student_name, s.branch, " +
                        "s.section, r.semester, r.subject1, r.subject2, " +
                        "r.subject3, r.subject4, r.subject5, r.subject6, " +
                        "r.total, r.percentage, r.status " +
                        "FROM students s " +
                        "INNER JOIN results r ON s.student_id = r.student_id " +
                        "WHERE s.student_id = ? " +
                        "AND s.student_name = ? " +
                        "AND s.branch = ? " +
                        "AND s.section = ? " +
                        "AND r.semester = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ps.setString(2, studentName);
            ps.setString(3, branch);
            ps.setString(4, section);
            ps.setString(5, semester);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String report =
                            "STUDENT RESULT REPORT\n\n" +
                                    "Student ID: " + rs.getString("student_id") +
                                    "\nStudent Name: " + rs.getString("student_name") +
                                    "\nBranch: " + rs.getString("branch") +
                                    "\nSection: " + rs.getString("section") +
                                    "\nSemester: " + rs.getString("semester") +
                                    "\n\nSUBJECT MARKS\n" +
                                    "Subject 1: " + rs.getInt("subject1") +
                                    "\nSubject 2: " + rs.getInt("subject2") +
                                    "\nSubject 3: " + rs.getInt("subject3") +
                                    "\nSubject 4: " + rs.getInt("subject4") +
                                    "\nSubject 5: " + rs.getInt("subject5") +
                                    "\nSubject 6: " + rs.getInt("subject6") +
                                    "\n\nTotal: " + rs.getInt("total") +
                                    " / 600" +
                                    "\nPercentage: " +
                                    rs.getBigDecimal("percentage") + "%" +
                                    "\nStatus: " + rs.getString("status");

                    JTextArea reportArea = new JTextArea(report);
                    reportArea.setEditable(false);
                    reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                    reportArea.setMargin(new Insets(12, 12, 12, 12));

                    JScrollPane scrollPane =
                            new JScrollPane(reportArea);
                    scrollPane.setPreferredSize(new Dimension(420, 400));

                    JOptionPane.showMessageDialog(
                            this,
                            scrollPane,
                            "Student Result Report",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "No matching result found.\n\n" +
                                    "Check the student details and make sure " +
                                    "marks have been entered for this semester.",
                            "Report Not Found",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Could not generate report.\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void addFormRow(
            JPanel panel,
            JLabel label,
            JComponent component,
            int row) {

        GridBagConstraints labelGbc = new GridBagConstraints();
        labelGbc.gridx = 0;
        labelGbc.gridy = row;
        labelGbc.weightx = 0;
        labelGbc.fill = GridBagConstraints.HORIZONTAL;
        labelGbc.insets = new Insets(7, 40, 7, 15);
        panel.add(label, labelGbc);

        GridBagConstraints fieldGbc = new GridBagConstraints();
        fieldGbc.gridx = 1;
        fieldGbc.gridy = row;
        fieldGbc.gridwidth = 3;
        fieldGbc.weightx = 1;
        fieldGbc.fill = GridBagConstraints.HORIZONTAL;
        fieldGbc.insets = new Insets(7, 0, 7, 40);
        panel.add(component, fieldGbc);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 15));
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Reports().setVisible(true));
    }
}
