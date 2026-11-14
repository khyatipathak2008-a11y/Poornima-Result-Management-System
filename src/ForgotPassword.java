
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ForgotPassword extends JFrame {

    // COLOURS
    Color blue = new Color(37, 42, 114);
    Color borderBlue = new Color(190, 200, 220);

    // FIELDS
    JTextField usernameField;
    JPasswordField newPasswordField;
    JPasswordField confirmPasswordField;

    // CONSTRUCTOR
    public ForgotPassword() {

        setTitle("Forgot Password - Result Management System");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());

        // HEADER / POORNIMA LOGO
        java.net.URL logoURL = getClass().getResource("/login.png");

        if (logoURL != null) {
            ImageIcon logo = new ImageIcon(logoURL);
            Image image = logo.getImage();
            Image resizedImage = image.getScaledInstance(
                    850, 150, Image.SCALE_SMOOTH);

            JLabel header = new JLabel(new ImageIcon(resizedImage));
            header.setHorizontalAlignment(JLabel.CENTER);
            header.setBackground(Color.WHITE);
            header.setOpaque(true);
            header.setBorder(BorderFactory.createEmptyBorder(
                    8, 15, 8, 15));

            add(header, BorderLayout.NORTH);
        } else {
            JLabel header = new JLabel("");
            header.setPreferredSize(new Dimension(0, 166));
            header.setBackground(Color.WHITE);
            header.setOpaque(true);
            add(header, BorderLayout.NORTH);
        }

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        // CARD
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(borderBlue, 1));
        card.setPreferredSize(new Dimension(560, 440));
        card.setMinimumSize(new Dimension(560, 440));
        card.setMaximumSize(new Dimension(560, 440));
        card.setLayout(null);

        // TITLE
        JLabel title = new JLabel("RESET PASSWORD");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(blue);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(80, 30, 400, 40);
        card.add(title);

        // SUBTITLE
        JLabel subtitle = new JLabel(
                "Create a new administrator password");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setHorizontalAlignment(JLabel.CENTER);
        subtitle.setBounds(60, 75, 440, 30);
        card.add(subtitle);

        // SMALL BLUE LINE
        JPanel blueLine = new JPanel();
        blueLine.setBackground(blue);
        blueLine.setBounds(210, 112, 140, 3);
        card.add(blueLine);

        // USERNAME
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        usernameLabel.setBounds(50, 140, 120, 30);
        card.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 15));
        usernameField.setBounds(180, 135, 330, 38);
        card.add(usernameField);

        // NEW PASSWORD
        JLabel newPasswordLabel = new JLabel("New Password");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 15));
        newPasswordLabel.setBounds(50, 195, 120, 30);
        card.add(newPasswordLabel);

        newPasswordField = new JPasswordField();
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
        newPasswordField.setBounds(180, 190, 330, 38);
        card.add(newPasswordField);

        // CONFIRM PASSWORD
        JLabel confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setFont(new Font("Arial", Font.BOLD, 15));
        confirmLabel.setBounds(50, 250, 120, 30);
        card.add(confirmLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
        confirmPasswordField.setBounds(180, 245, 330, 38);
        card.add(confirmPasswordField);

        // RESET PASSWORD BUTTON
        JButton resetButton = new JButton("RESET PASSWORD");
        resetButton.setBounds(50, 310, 460, 45);
        resetButton.setBackground(blue);
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(new Font("Arial", Font.BOLD, 15));
        resetButton.setFocusPainted(false);
        resetButton.setBorderPainted(false);
        card.add(resetButton);

        // BACK TO LOGIN
        JButton backButton = new JButton("Back to Admin Login");
        backButton.setBounds(150, 370, 260, 30);
        backButton.setForeground(blue);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        card.add(backButton);

        // ADD CARD TO CENTER
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(card, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // FOOTER
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(blue);
        footer.setPreferredSize(new Dimension(0, 40));

        JLabel footerText = new JLabel(
                "Result Management System | Developed by Khyati Pathak | B1 Batch",
                SwingConstants.CENTER);

        footerText.setForeground(Color.WHITE);
        footerText.setFont(new Font("Arial", Font.PLAIN, 14));
        footer.add(footerText, BorderLayout.CENTER);

        add(footer, BorderLayout.SOUTH);

        // RESET PASSWORD ACTION: UPDATE MYSQL
        resetButton.addActionListener(e -> {

            String username = usernameField.getText().trim();
            String newPassword =
                    new String(newPasswordField.getPassword());
            String confirmPassword =
                    new String(confirmPasswordField.getPassword());

            // Check empty fields
            if (username.isEmpty()
                    || newPassword.isEmpty()
                    || confirmPassword.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Check password match
            if (!newPassword.equals(confirmPassword)) {

                JOptionPane.showMessageDialog(
                        this,
                        "New password and confirm password do not match.",
                        "Password Mismatch",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Update the existing user's password in MySQL
            String checkSql =
                    "SELECT username FROM users WHERE username = ?";

            String updateSql =
                    "UPDATE users SET password = ? WHERE username = ?";

            try (Connection con = DBConnection.getConnection()) {

                // Verify username exists
                try (PreparedStatement check =
                             con.prepareStatement(checkSql)) {

                    check.setString(1, username);

                    try (ResultSet rs = check.executeQuery()) {

                        if (!rs.next()) {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Username not found.",
                                    "Reset Failed",
                                    JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                }

                // Save new password
                try (PreparedStatement update =
                             con.prepareStatement(updateSql)) {

                    update.setString(1, newPassword);
                    update.setString(2, username);

                    int rowsUpdated = update.executeUpdate();

                    if (rowsUpdated > 0) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Password reset successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);

                        new AdminLogin().setVisible(true);
                        dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Password could not be reset.",
                                "Reset Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database error: " + ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // BACK BUTTON ACTION
        backButton.addActionListener(e -> {
            new AdminLogin().setVisible(true);
            dispose();
        });
    }

    // MAIN METHOD
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ForgotPassword window = new ForgotPassword();
            window.setVisible(true);
        });
    }
}
