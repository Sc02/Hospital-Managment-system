import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JButton signUpButton;
    private JButton pharmacyButton;

    public LoginFrame() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("login_background.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

         // Top panel that will contain both the heading and the nav bar
         JPanel topPanel = new JPanel();
         topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
         contentPane.add(topPanel, BorderLayout.NORTH);
 
         // Heading panel
         JPanel headingPanel = new JPanel();
         headingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
         JLabel headingLabel = new JLabel("Pilani General Hospital");
         headingLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
         headingPanel.add(headingLabel);
         topPanel.add(headingPanel);
 
         // Nav bar panel
         JPanel navBarPanel = new JPanel();
         navBarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
         pharmacyButton = new JButton("Pharmacy");
         navBarPanel.add(pharmacyButton);
         topPanel.add(navBarPanel);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // Make it transparent to show the background image
        contentPane.add(centerPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 50, 10, 50);

        // Adding labels for username and password
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        signInButton = new JButton("Sign In");
        signUpButton = new JButton("Sign Up");

        // Set minimum sizes for the text fields
        usernameField.setMinimumSize(new Dimension(150, 20));
        passwordField.setMinimumSize(new Dimension(150, 20));

        centerPanel.add(usernameLabel, gbc);
        centerPanel.add(usernameField, gbc);
        centerPanel.add(passwordLabel, gbc);
        centerPanel.add(passwordField, gbc);

        gbc.fill = GridBagConstraints.NONE; // Prevents buttons from stretching
        centerPanel.add(signInButton, gbc);
        centerPanel.add(signUpButton, gbc);

        // Additional label under the "Sign Up" button
        JLabel signUpHintLabel = new JLabel("(Please click on this button if you do not have an account)");
        signUpHintLabel.setForeground(Color.RED); // Set text color to red
        signUpHintLabel.setFont(new Font("Arial", Font.PLAIN, 10)); // Set font size to small
        gbc.insets = new Insets(0, 50, 10, 50); // Adjust top inset to bring the label closer to the "Sign Up" button
        centerPanel.add(signUpHintLabel, gbc);

        // Footer panel with "Group 32"
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel footerLabel = new JLabel("Group 32");
        footerPanel.add(footerLabel);
        contentPane.add(footerPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginFrame frame = new LoginFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}