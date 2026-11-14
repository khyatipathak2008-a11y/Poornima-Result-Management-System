
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchResult extends JFrame {

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

    public SearchResult() {

        setTitle("Search Student Result - Result Management System");
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

        // Search form
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(lightBackground);
        searchPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        searchPanel.setLayout(null);
        searchPanel.setPreferredSize(new Dimension(660, 500));
        searchPanel.setMinimumSize(new Dimension(660, 500));
        searchPanel.setMaximumSize(new Dimension(660, 500));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        mainPanel.add(searchPanel, gbc);

        // Title
        JLabel title = new JLabel("SEARCH STUDENT RESULT");
        title.setFont(new Font("Arial", Font.BOLD, 27));
        title.setForeground(blue);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(120, 20, 420, 40);
        searchPanel.add(title);

        JPanel line = new JPanel();
        line.setBackground(blue);
        line.setBounds(280, 65, 100, 3);
        searchPanel.add(line);

        // Student ID
        JLabel idLabel = createLabel("Student ID");
        idLabel.setBounds(55, 100, 130, 30);
        searchPanel.add(idLabel);

        idField = createTextField();
        idField.setBounds(200, 95, 370, 40);
        searchPanel.add(idField);

        // Student Name
        JLabel nameLabel = createLabel("Student Name");
        nameLabel.setBounds(55, 150, 130, 30);
        searchPanel.add(nameLabel);

        nameField = createTextField();
        nameField.setBounds(200, 145, 370, 40);
        searchPanel.add(nameField);

        // Branch
        JLabel branchLabel = createLabel("Branch");
        branchLabel.setBounds(55, 200, 130, 30);
        searchPanel.add(branchLabel);

        String[] branches = {
                "All Branches",
                "CSE Core",
                "CSE with AI",
                "CSE with IoT",
                "CSE Regional",
                "AI & DS",
                "CS & IT"
        };

        branchBox = new JComboBox<>(branches);
        branchBox.setBounds(200, 195, 370, 40);
        branchBox.setFont(new Font("Arial", Font.PLAIN, 14));
        searchPanel.add(branchBox);

        // Section
        JLabel sectionLabel = createLabel("Section");
        sectionLabel.setBounds(55, 250, 130, 30);
        searchPanel.add(sectionLabel);

        String[] sections = {"All Sections", "A", "B", "C", "D"};

        sectionBox = new JComboBox<>(sections);
        sectionBox.setBounds(200, 245, 170, 40);
        sectionBox.setFont(new Font("Arial", Font.PLAIN, 14));
        searchPanel.add(sectionBox);

        // Semester
        JLabel semesterLabel = createLabel("Semester");
        semesterLabel.setBounds(390, 250, 80, 30);
        searchPanel.add(semesterLabel);

        String[] semesters = {
                "All", "1st", "2nd", "3rd", "4th",
                "5th", "6th", "7th", "8th"
        };

        semesterBox = new JComboBox<>(semesters);
        semesterBox.setBounds(475, 245, 95, 40);
        semesterBox.setFont(new Font("Arial", Font.PLAIN, 12));
        searchPanel.add(semesterBox);

        // Search button
        JButton searchButton = new JButton("SEARCH RESULT");
        searchButton.setBounds(200, 325, 370, 45);
        searchButton.setBackground(blue);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder());
        searchPanel.add(searchButton);

        // Clear button
        JButton clearButton = new JButton("CLEAR");
        clearButton.setBounds(200, 385, 175, 40);
        clearButton.setBackground(Color.WHITE);
        clearButton.setForeground(blue);
        clearButton.setFont(new Font("Arial", Font.BOLD, 13));
        clearButton.setFocusPainted(false);
        clearButton.setBorder(BorderFactory.createLineBorder(borderColor));
        searchPanel.add(clearButton);

        // Back button
        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setBounds(395, 385, 175, 40);
        backButton.setForeground(blue);
        backButton.setFont(new Font("Arial", Font.BOLD, 13));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        searchPanel.add(backButton);

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

        // Search from database
        searchButton.addActionListener(e -> searchResults());

        // Clear fields
        clearButton.addActionListener(e -> clearForm());

        // Back to dashboard
        backButton.addActionListener(e -> {
            new Dashboard().setVisible(true);
            dispose();
        });
    }

    private void searchResults() {

        String studentId = idField.getText().trim();
        String studentName = nameField.getText().trim();

        String branch = (String) branchBox.getSelectedItem();
        String section = (String) sectionBox.getSelectedItem();
        String semester = (String) semesterBox.getSelectedItem();

        if (studentId.isEmpty() && studentName.isEmpty()
                && branchBox.getSelectedIndex() == 0
                && sectionBox.getSelectedIndex() == 0
                && semesterBox.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a Student ID/name or select a filter.",
                    "Search Result",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!studentName.isEmpty()
                && !studentName.matches("[a-zA-Z ]+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student name should contain only letters and spaces.",
                    "Invalid Student Name",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder sql = new StringBuilder(
                "SELECT s.student_id, s.student_name, s.branch, s.section, "
                        + "r.semester, r.subject1, r.subject2, r.subject3, "
                        + "r.subject4, r.subject5, r.subject6, r.total, "
                        + "r.percentage, r.status "
                        + "FROM students s "
                        + "INNER JOIN results r ON s.student_id = r.student_id "
                        + "WHERE 1=1 ");

        if (!studentId.isEmpty()) {
            sql.append("AND s.student_id = ? ");
        }

        if (!studentName.isEmpty()) {
            sql.append("AND s.student_name LIKE ? ");
        }

        if (!"All Branches".equals(branch)) {
            sql.append("AND s.branch = ? ");
        }

        if (!"All Sections".equals(section)) {
            sql.append("AND s.section = ? ");
        }

        if (!"All".equals(semester)) {
            sql.append("AND r.semester = ? ");
        }

        sql.append("ORDER BY s.student_name, r.semester");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int index = 1;

            if (!studentId.isEmpty()) {
                ps.setString(index++, studentId);
            }

            if (!studentName.isEmpty()) {
                ps.setString(index++, "%" + studentName + "%");
            }

            if (!"All Branches".equals(branch)) {
                ps.setString(index++, branch);
            }

            if (!"All Sections".equals(section)) {
                ps.setString(index++, section);
            }

            if (!"All".equals(semester)) {
                ps.setString(index++, semester);
            }

            try (ResultSet rs = ps.executeQuery()) {

                StringBuilder output = new StringBuilder();
                int count = 0;

                while (rs.next()) {
                    count++;

                    output.append("RESULT ").append(count).append("\n");
                    output.append("--------------------------------\n");
                    output.append("Student ID: ")
                            .append(rs.getString("student_id")).append("\n");
                    output.append("Name: ")
                            .append(rs.getString("student_name")).append("\n");
                    output.append("Branch: ")
                            .append(rs.getString("branch")).append("\n");
                    output.append("Section: ")
                            .append(rs.getString("section")).append("\n");
                    output.append("Semester: ")
                            .append(rs.getString("semester")).append("\n\n");

                    for (int i = 1; i <= 6; i++) {
                        int mark = rs.getInt("subject" + i);

                        output.append("Subject ").append(i).append(": ")
                                .append(mark).append(" - ")
                                .append(mark >= 40 ? "PASS" : "FAIL")
                                .append("\n");
                    }

                    output.append("\nTotal: ")
                            .append(rs.getInt("total")).append(" / 600");
                    output.append("\nPercentage: ")
                            .append(String.format(
                                    "%.2f", rs.getDouble("percentage")))
                            .append("%");
                    output.append("\nOverall Result: ")
                            .append(rs.getString("status"));
                    output.append("\n\n\n");
                }

                if (count == 0) {
                    JOptionPane.showMessageDialog(
                            this,
                            "No saved results found for the selected search.",
                            "No Results Found",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                JTextArea resultArea = new JTextArea(output.toString());
                resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
                resultArea.setEditable(false);
                resultArea.setCaretPosition(0);

                JScrollPane scrollPane = new JScrollPane(resultArea);
                scrollPane.setPreferredSize(new Dimension(560, 430));

                JOptionPane.showMessageDialog(
                        this,
                        scrollPane,
                        "Search Results (" + count + ")",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to search the database.\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        branchBox.setSelectedIndex(0);
        sectionBox.setSelectedIndex(0);
        semesterBox.setSelectedIndex(0);
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

    // Run this screen independently.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new SearchResult().setVisible(true));
    }
}
