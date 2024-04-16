import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PharmacyPage extends JFrame {
    private JPanel contentPane;
    private LoginFrame loginFrame; 

    public PharmacyPage(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;

        setTitle("Pharmacy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Adjust the path to your background image as necessary
                g.drawImage(new ImageIcon("pharmacy_background.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Navigation bar panel
        JPanel navBarPanel = new JPanel(new BorderLayout());
        contentPane.add(navBarPanel, BorderLayout.NORTH);

        // Heading label
        JLabel headingLabel = new JLabel("Pharmacy");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        navBarPanel.add(headingLabel, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Here, you can define what happens when the "Back" button is clicked.
                // For example, you might want to close this window and show the previous one.
                dispose(); // Close the current window
                // new PreviousPage().setVisible(true); // Open the previous page, adjust as necessary
            }
        });

        // Back button
        
        backButton.addActionListener(e -> {
            this.setVisible(false); // Hide PharmacyPage
            loginFrame.setVisible(true); // Show LoginFrame
        });
        navBarPanel.add(backButton, BorderLayout.EAST);

        // Adjustments to ensure the nav bar and heading look good
        navBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public PharmacyPage() {
        
        setTitle("Pharmacy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Adjust the path to your background image as necessary
                g.drawImage(new ImageIcon("pharmacy_background.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Navigation bar panel
        JPanel navBarPanel = new JPanel(new BorderLayout());
        contentPane.add(navBarPanel, BorderLayout.NORTH);

        // Heading label
        JLabel headingLabel = new JLabel("Pharmacy");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        navBarPanel.add(headingLabel, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Here, you can define what happens when the "Back" button is clicked.
                // For example, you might want to close this window and show the previous one.
                dispose(); // Close the current window
                // new PreviousPage().setVisible(true); // Open the previous page, adjust as necessary
            }
        });
        navBarPanel.add(backButton, BorderLayout.EAST);

        // Back button
        
        backButton.addActionListener(e -> {
            this.setVisible(false); // Hide PharmacyPage
            loginFrame.setVisible(true); // Show LoginFrame
        });
        navBarPanel.add(backButton, BorderLayout.EAST);

        // Adjustments to ensure the nav bar and heading look good
        navBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        EventQueue.invokeLater(() -> {
            try {
                PharmacyPage frame = new PharmacyPage(loginFrame);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}