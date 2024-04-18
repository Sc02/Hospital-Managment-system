import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class DoctorsFrame extends JFrame {

    protected static final String patientID = null;
    private JPanel contentPane;

    public DoctorsFrame() {
        setTitle("Doctor Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout()); // Use BorderLayout
        setContentPane(contentPane);

        // Create sign out button
        JButton signOutButton = new JButton("Sign Out");
        contentPane.add(signOutButton, BorderLayout.NORTH);
        signOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                // Open the login page
                new LoginFrame().setVisible(true);
            }
        });

        // Create "Available Wards" button
        JButton availableWardsButton = new JButton("Available Wards");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel for centering
        buttonPanel.add(availableWardsButton);
        contentPane.add(buttonPanel, BorderLayout.CENTER); // Add to center
        availableWardsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                try {
                    // Open the WardPage
                    new WardPage(patientID).setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DoctorsFrame frame = new DoctorsFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
