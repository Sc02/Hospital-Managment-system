import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Admin extends JFrame {
    private JPanel contentPane;
    private static String adminID;

    public Admin(String adminID) {
        Admin.adminID = adminID;
        setTitle("Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600)); // Adjusted for additional fields
        setLocationRelativeTo(null); // Center the window

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Adjust path to your background image
                g.drawImage(new ImageIcon("admin_background.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Navigation bar panel
        JPanel navBarPanel = new JPanel(new BorderLayout());
        
        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setOpaque(true);
        signOutButton.setBorderPainted(false);
        signOutButton.setBackground(Color.RED);
        signOutButton.setForeground(Color.WHITE);
        navBarPanel.setOpaque(false); // Make navBarPanel transparent
        
        navBarPanel.add(signOutButton, BorderLayout.EAST);
        contentPane.add(navBarPanel, BorderLayout.NORTH);

        // Heading label
        JLabel headingLabel = new JLabel("      ADMIN");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        navBarPanel.add(headingLabel, BorderLayout.CENTER);

        // Main content panel with BoxLayout for vertical alignment
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setOpaque(false); // Make mainContentPanel transparent
        JScrollPane scrollPane = new JScrollPane(mainContentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Adding components to the main content panel
        addComponentWithLabel(mainContentPanel, "Add Doctor:", 3); // 4 additional fields for "Add Doctor"
        addComponentWithLabel(mainContentPanel, "Add Medicine:", 2); // 3 additional fields for "Add Medicine"

         // Add the four buttons to the main content panel
    addButton(mainContentPanel, "Appointments");
    addButton(mainContentPanel, "Payments");
    addButton(mainContentPanel, "Wards");
    addButton(mainContentPanel, "Salaries");

    pack(); // Adjusts window size to fit components
}

private void addButton(JPanel panel, String buttonText) {
    JButton button = new JButton(buttonText);
    JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    flowPanel.setOpaque(false); // Make flowPanel transparent
    flowPanel.add(button);
    panel.add(flowPanel);
}

    private void addComponentWithLabel(JPanel panel, String labelText, int additionalFields) {
        JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanel.setOpaque(false); // Make flowPanel transparent
        JLabel label = new JLabel(labelText);
        JTextField textField = createPlaceholderTextField("Main Field");
        JButton button = new JButton(labelText.split(" ")[0]); // Uses first word of label as button text
        flowPanel.add(label);
        flowPanel.add(textField);
        for (int i = 0; i < additionalFields; i++) {
            JTextField additionalField = createPlaceholderTextField("Field " + (i + 1));
            flowPanel.add(additionalField);
        }
        flowPanel.add(button);
        panel.add(flowPanel);
    }

    private JTextField createPlaceholderTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder, 10);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
        return textField;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Admin frame = new Admin(adminID);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}