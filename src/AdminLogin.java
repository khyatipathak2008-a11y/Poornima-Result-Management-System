
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AdminLogin extends JFrame {

    // ==============================
    // COLORS
    // ==============================

    Color blue = new Color(37, 42, 114);
    Color borderColor = new Color(205, 210, 225);

    // ==============================
    // LOGIN FIELDS
    // ==============================

    JTextField usernameField;
    JPasswordField passwordField;

    public AdminLogin() {

        // ==============================
        // WINDOW SETTINGS
        // ==============================

        setTitle("Admin Login - Result Management System");
        setSize(1000, 750);
        setMinimumSize(new Dimension(900, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // ==============================
        // HEADER
        // ==============================

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        );

        ImageIcon logo = new ImageIcon(
                getClass().getResource("/login.png")
        );

        Image image = logo.getImage();
        Image resizedImage = image.getScaledInstance(
                850, 150, Image.SCALE_SMOOTH
        );

        JLabel header = new JLabel(new ImageIcon(resizedImage));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(header, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // ==============================
        // CENTER AREA
        // ==============================

        JPanel centerArea = new JPanel(new GridBagLayout());
        centerArea.setBackground(Color.WHITE);

        // ==============================
        // LOGIN BOX
        // ==============================

        JPanel loginBox = new JPanel();
        loginBox.setBackground(Color.WHITE);
        loginBox.setPreferredSize(new Dimension(580, 430));
        loginBox.setBorder(new LineBorder(borderColor, 1));
        loginBox.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 48, 0, 48);
        gbc.weightx = 1.0;

        // ==============================
        // TITLE
        // ==============================

        JLabel title = new JLabel(
                "ADMIN LOGIN",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(blue);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(28, 48, 12, 48);
        loginBox.add(title, gbc);

        // ==============================
        // SUBTITLE
        // ==============================

        JLabel subtitle = new JLabel(
                "B.Tech Result Management System",
                SwingConstants.CENTER
        );

        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setForeground(new Color(30, 30, 30));

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 48, 10, 48);
        loginBox.add(subtitle, gbc);

        // ==============================
        // SMALL BLUE LINE
        // ==============================

        JPanel blueLine = new JPanel();
        blueLine.setBackground(blue);
        blueLine.setPreferredSize(new Dimension(100, 3));

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 48, 25, 48);
        loginBox.add(blueLine, gbc);

        // ==============================
        // USERNAME LABEL
        // ==============================

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 48, 8, 15);
        loginBox.add(usernameLabel, gbc);

        // ==============================
        // USERNAME FIELD
        // ==============================

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 15));
        usernameField.setPreferredSize(new Dimension(300, 42));
        usernameField.setBorder(
                new LineBorder(new Color(180, 190, 205), 1)
        );

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 8, 48);
        loginBox.add(usernameField, gbc);

        // ==============================
        // PASSWORD LABEL
        // ==============================

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(8, 48, 8, 15);
        loginBox.add(passwordLabel, gbc);

        // ==============================
        // PASSWORD FIELD
        // ==============================

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField.setPreferredSize(new Dimension(300, 42));
        passwordField.setBorder(
                new LineBorder(new Color(180, 190, 205), 1)
        );

        gbc.gridx = 1;
        gbc.insets = new Insets(8, 0, 18, 48);
        loginBox.add(passwordField, gbc);

        // ==============================
        // LOGIN BUTTON
        // ==============================

        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(blue);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setPreferredSize(new Dimension(300, 48));

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 48, 18, 48);
        loginBox.add(loginButton, gbc);

        // ==============================
        // FORGOT PASSWORD
        // ==============================

        JButton forgotButton = new JButton("Forgot Password?");
        forgotButton.setFont(new Font("Arial", Font.BOLD, 14));
        forgotButton.setForeground(blue);
        forgotButton.setBorderPainted(false);
        forgotButton.setContentAreaFilled(false);
        forgotButton.setFocusPainted(false);
        forgotButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridy = 6;
        gbc.insets = new Insets(0, 48, 25, 48);
        loginBox.add(forgotButton, gbc);

        // ==============================
        // ADD LOGIN BOX TO CENTER
        // ==============================

        GridBagConstraints centerGbc = new GridBagConstraints();
        centerGbc.gridx = 0;
        centerGbc.gridy = 0;
        centerGbc.anchor = GridBagConstraints.CENTER;

        centerArea.add(loginBox, centerGbc);
        add(centerArea, BorderLayout.CENTER);

        // ==============================
        // FOOTER
        // ==============================

        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(blue);
        footer.setPreferredSize(new Dimension(0, 40));

        JLabel footerText = new JLabel(
                "Result Management System | Developed by Khyati Pathak | B1 Batch",
                SwingConstants.CENTER
        );

        footerText.setForeground(Color.WHITE);
        footerText.setFont(new Font("Arial", Font.PLAIN, 14));

        footer.add(footerText, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        // ==============================
        // LOGIN ACTION - MYSQL
        // ==============================

        loginButton.addActionListener(e -> {

            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter username and password.",
                        "Login",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String sql = "SELECT username FROM users " +
                    "WHERE username = ? AND password = ? AND role = 'ADMIN'";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, username);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Login Successful!",
                                "Login",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        new Dashboard().setVisible(true);
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Invalid username or password.",
                                "Login Failed",
                                JOptionPane.ERROR_MESSAGE
                        );

                        passwordField.setText("");
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed:\n" + ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
        });

        // ==============================
        // FORGOT PASSWORD ACTION
        // ==============================

        forgotButton.addActionListener(e -> {
            new ForgotPassword().setVisible(true);
            dispose();
        });

        // ==============================
        // ENTER KEY = LOGIN
        // ==============================

        getRootPane().setDefaultButton(loginButton);

        // ==============================
        // SHOW WINDOW
        // ==============================

        setVisible(true);
    }

    // ==============================
    // MAIN METHOD
    // ==============================

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminLogin::new);
    }
}
