import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PatientPage extends JFrame {

    private JPanel contentPane;
    private LoginFrame loginFrame;
    private JTextField feedbackTextField;
    private JTextField doctorNameTextField;

    public PatientPage() {
        setTitle("Patient Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set the background image
                ImageIcon imageIcon = new ImageIcon("patient_background.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(null); // Use absolute layout
        setContentPane(contentPane);

        // Create sign out button
        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(650, 10, 120, 30);
        contentPane.add(signOutButton);
        signOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current PatientPage window
                dispose();
                // Open the login page
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });

        // Create appointments button
        JButton appointmentsButton = new JButton("Appointments");
        appointmentsButton.setBounds(20, 50, 150, 30);
        contentPane.add(appointmentsButton);
        appointmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the appointment page
                AppointmentPage appointmentPage = new AppointmentPage();
                appointmentPage.setVisible(true);
            }
        });

        // Create doctor name text field
        JLabel doctorNameLabel = new JLabel("Doctor Name:");
        doctorNameLabel.setBounds(20, 100, 100, 20);
        contentPane.add(doctorNameLabel);
        doctorNameTextField = new JTextField();
        doctorNameTextField.setBounds(120, 100, 200, 30);
        contentPane.add(doctorNameTextField);

        // Create feedback text field
        JLabel feedbackLabel = new JLabel("Feedback:");
        feedbackLabel.setBounds(20, 150, 80, 20);
        contentPane.add(feedbackLabel);
        feedbackTextField = new JTextField();
        feedbackTextField.setBounds(120, 150, 200, 30);
        contentPane.add(feedbackTextField);

        // Create submit feedback button
        JButton submitFeedbackButton = new JButton("Submit Feedback");
        submitFeedbackButton.setBounds(20, 200, 150, 30);
        contentPane.add(submitFeedbackButton);

        // Create pharmacy button
        JButton pharmacyButton = new JButton("Pharmacy");
        pharmacyButton.setBounds(20, 250, 150, 30);
        contentPane.add(pharmacyButton);
        pharmacyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the pharmacy page
                PharmacyPage pharmacyPage = new PharmacyPage(loginFrame);
                pharmacyPage.setVisible(true);
            }
        });
        
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PatientPage frame = new PatientPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
