import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Dashboard extends JFrame {

    // ==============================
    // COLOURS
    // ==============================

    Color blue = new Color(37, 42, 114);
    Color lightBlue = new Color(242, 245, 250);
    Color borderBlue = new Color(210, 216, 230);
    Color darkText = new Color(35, 35, 45);


    // ==============================
    // CONSTRUCTOR
    // ==============================

    public Dashboard() {

        // ==============================
        // WINDOW SETTINGS
        // ==============================

        setTitle("Dashboard - Result Management System");

        setSize(1000, 750);

        setMinimumSize(new Dimension(900, 650));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        // Allow maximize / resize
        setResizable(true);

        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.WHITE);


        // ==============================
        // HEADER
        // ==============================

        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.setBackground(Color.WHITE);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 20, 10, 20
                )
        );


        ImageIcon logo = new ImageIcon(
                getClass().getResource("/login.png")
        );

        Image image = logo.getImage();

        Image resizedImage = image.getScaledInstance(
                850,
                150,
                Image.SCALE_SMOOTH
        );

        JLabel header = new JLabel(
                new ImageIcon(resizedImage)
        );

        header.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        headerPanel.add(
                header,
                BorderLayout.CENTER
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );


        // ==============================
        // MAIN AREA
        // ==============================

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(Color.WHITE);


        // ==============================
        // LEFT NAVIGATION
        // ==============================

        JPanel sidePanel = new JPanel();

        sidePanel.setBackground(lightBlue);

        sidePanel.setPreferredSize(
                new Dimension(220, 0)
        );

        sidePanel.setBorder(
                BorderFactory.createMatteBorder(
                        0,
                        0,
                        0,
                        1,
                        borderBlue
                )
        );


        // Compact spacing
        sidePanel.setLayout(
                new BoxLayout(
                        sidePanel,
                        BoxLayout.Y_AXIS
                )
        );


        // ==============================
        // NAVIGATION BUTTONS
        // ==============================

        JButton dashboardButton =
                createMenuButton("Dashboard");

        JButton studentButton =
                createMenuButton("Student Details");

        JButton resultButton =
                createMenuButton("Enter Result");

        JButton searchButton =
                createMenuButton("Search Result");

        JButton reportButton =
                createMenuButton("Reports");

        JButton logoutButton =
                createMenuButton("Logout");


        // ==============================
        // ADD MENU BUTTONS
        // ==============================

        sidePanel.add(dashboardButton);
        sidePanel.add(studentButton);
        sidePanel.add(resultButton);
        sidePanel.add(searchButton);
        sidePanel.add(reportButton);


        // Small flexible space before Logout

        sidePanel.add(
                Box.createVerticalGlue()
        );

        sidePanel.add(logoutButton);


        mainPanel.add(
                sidePanel,
                BorderLayout.WEST
        );


        // ==============================
        // CONTENT AREA
        // ==============================

        JPanel contentPanel =
                new JPanel(new GridBagLayout());

        contentPanel.setBackground(Color.WHITE);


        // ==============================
        // CONTENT CONTAINER
        // ==============================

        JPanel contentContainer =
                new JPanel(new BorderLayout());

        contentContainer.setBackground(Color.WHITE);

        contentContainer.setPreferredSize(
                new Dimension(700, 500)
        );


        // ==============================
        // TOP CONTENT
        // ==============================

        JPanel topContent =
                new JPanel();

        topContent.setBackground(Color.WHITE);

        topContent.setLayout(
                new BoxLayout(
                        topContent,
                        BoxLayout.Y_AXIS
                )
        );


        // ==============================
        // WELCOME
        // ==============================

        JLabel welcome =
                new JLabel("Welcome, Admin");

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        welcome.setForeground(blue);

        welcome.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        // ==============================
        // SUBTITLE
        // ==============================

        JLabel subtitle =
                new JLabel(
                        "B.Tech Result Management System"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        subtitle.setForeground(darkText);

        subtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        // ==============================
        // DESCRIPTION
        // ==============================

        JLabel description =
                new JLabel(
                        "<html>"
                                + "Manage student information, enter results,<br>"
                                + "search records and generate reports from one place."
                                + "</html>"
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        description.setForeground(
                new Color(80, 80, 90)
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        // ==============================
        // ADD TOP CONTENT
        // ==============================

        topContent.add(welcome);

        topContent.add(
                Box.createVerticalStrut(18)
        );

        topContent.add(subtitle);

        topContent.add(
                Box.createVerticalStrut(32)
        );

        topContent.add(description);


        // ==============================
        // LOWER CONTENT
        // ==============================

        JPanel lowerContent =
                new JPanel(new GridBagLayout());

        lowerContent.setBackground(Color.WHITE);


        GridBagConstraints lowerGbc =
                new GridBagConstraints();

        lowerGbc.fill =
                GridBagConstraints.BOTH;

        lowerGbc.weighty = 1.0;

        lowerGbc.anchor =
                GridBagConstraints.NORTHWEST;


        // ==============================
        // BRANCH SECTION
        // ==============================

        JPanel branchPanel =
                new JPanel();

        branchPanel.setBackground(Color.WHITE);

        branchPanel.setLayout(
                new BoxLayout(
                        branchPanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel branchTitle =
                new JLabel("Available Branches");

        branchTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        branchTitle.setForeground(blue);

        branchTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        branchPanel.add(branchTitle);

        branchPanel.add(
                Box.createVerticalStrut(20)
        );


        String[] branches = {
                "CSE Core",
                "CSE with Specialization",
                "CSE Regional",
                "AI & DS",
                "CS & IT"
        };


        for (String branch : branches) {

            JLabel branchLabel =
                    new JLabel(
                            "•  " + branch
                    );

            branchLabel.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            16
                    )
            );

            branchLabel.setForeground(darkText);

            branchLabel.setAlignmentX(
                    Component.LEFT_ALIGNMENT
            );


            branchPanel.add(branchLabel);

            // Compact line spacing
            branchPanel.add(
                    Box.createVerticalStrut(12)
            );
        }


        // ==============================
        // ADD BRANCH PANEL
        // ==============================

        lowerGbc.gridx = 0;
        lowerGbc.gridy = 0;

        lowerGbc.weightx = 0.55;

        lowerGbc.insets =
                new Insets(
                        45,
                        0,
                        0,
                        30
                );

        lowerContent.add(
                branchPanel,
                lowerGbc
        );


        // ==============================
        // SYSTEM OVERVIEW
        // ==============================

        JPanel infoPanel =
                new JPanel();

        infoPanel.setBackground(
                new Color(248, 249, 252)
        );

        infoPanel.setBorder(
                new LineBorder(
                        borderBlue,
                        1
                )
        );

        infoPanel.setLayout(
                new BorderLayout()
        );


        // ==============================
        // INFORMATION TITLE
        // ==============================

        JLabel infoTitle =
                new JLabel(
                        "System Overview"
                );

        infoTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        infoTitle.setForeground(blue);

        infoTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        10,
                        20
                )
        );


        infoPanel.add(
                infoTitle,
                BorderLayout.NORTH
        );


        // ==============================
        // INFORMATION TEXT
        // ==============================

        JLabel infoText =
                new JLabel(
                        "<html>"
                                + "• Manage student details<br><br>"
                                + "• Enter semester results<br><br>"
                                + "• Search student results<br><br>"
                                + "• Generate student reports"
                                + "</html>"
                );

        infoText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        infoText.setForeground(darkText);

        infoText.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        25,
                        20,
                        20
                )
        );


        infoPanel.add(
                infoText,
                BorderLayout.CENTER
        );


        // ==============================
        // ADD INFORMATION PANEL
        // ==============================

        lowerGbc.gridx = 1;

        lowerGbc.gridy = 0;

        lowerGbc.weightx = 0.45;

        lowerGbc.insets =
                new Insets(
                        45,
                        0,
                        0,
                        0
                );

        lowerContent.add(
                infoPanel,
                lowerGbc
        );


        // ==============================
        // ADD CONTENT
        // ==============================

        contentContainer.add(
                topContent,
                BorderLayout.NORTH
        );

        contentContainer.add(
                lowerContent,
                BorderLayout.CENTER
        );


        // ==============================
        // CENTER CONTENT
        // ==============================

        GridBagConstraints contentGbc =
                new GridBagConstraints();

        contentGbc.gridx = 0;

        contentGbc.gridy = 0;

        contentGbc.weightx = 1.0;

        contentGbc.weighty = 1.0;

        contentGbc.fill =
                GridBagConstraints.BOTH;

        contentGbc.insets =
                new Insets(
                        45,
                        70,
                        35,
                        70
                );


        contentPanel.add(
                contentContainer,
                contentGbc
        );


        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        add(
                mainPanel,
                BorderLayout.CENTER
        );


        // ==============================
        // BUTTON ACTIONS
        // ==============================

        dashboardButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "You are already on the Dashboard."
            );

        });


        // ==============================
        // STUDENT DETAILS
        // ==============================

        studentButton.addActionListener(e -> {

            new StudentManagement()
                    .setVisible(true);

            dispose();

        });


        // ==============================
        // ENTER RESULT
        // ==============================

        resultButton.addActionListener(e -> {

            new ResultEntry()
                    .setVisible(true);

            dispose();

        });


        // ==============================
        // SEARCH RESULT
        // ==============================

        searchButton.addActionListener(e -> {

            new SearchResult()
                    .setVisible(true);

            dispose();

        });


        // ==============================
        // REPORTS
        // ==============================

        reportButton.addActionListener(e -> {

            new Reports()
                    .setVisible(true);

            dispose();

        });


        // ==============================
        // LOGOUT
        // ==============================

        logoutButton.addActionListener(e -> {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to logout?",
                            "Logout",
                            JOptionPane.YES_NO_OPTION
                    );


            if (choice ==
                    JOptionPane.YES_OPTION) {

                new AdminLogin()
                        .setVisible(true);

                dispose();
            }

        });


        // ==============================
        // FOOTER
        // ==============================

        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setBackground(blue);

        footer.setPreferredSize(
                new Dimension(
                        0,
                        38
                )
        );


        JLabel footerText =
                new JLabel(
                        "Result Management System | Developed by Khyati Pathak | B1 Batch",
                        SwingConstants.CENTER
                );

        footerText.setForeground(
                Color.WHITE
        );

        footerText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        footer.add(
                footerText,
                BorderLayout.CENTER
        );


        add(
                footer,
                BorderLayout.SOUTH
        );
    }


    // ==============================
    // MENU BUTTON DESIGN
    // ==============================

    private JButton createMenuButton(
            String text
    ) {

        JButton button =
                new JButton(text);


        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        button.setForeground(darkText);

        button.setBackground(lightBlue);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        25,
                        5,
                        10
                )
        );


        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );


        // Keep buttons compact
        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        55
                )
        );


        button.setPreferredSize(
                new Dimension(
                        220,
                        55
                )
        );


        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        return button;
    }


    // ==============================
    // MAIN METHOD
    // ==============================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(() -> {

            new Dashboard()
                    .setVisible(true);

        });

    }
}