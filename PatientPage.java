import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PatientPage extends JFrame {

    private JPanel contentPane;
    private JTextField feedbackTextField;
    private JComboBox<String> doctorNameDropdown; // Dropdown list for doctor names
    private static String patientID; 

    public PatientPage(String patientID) throws SQLException {
        PatientPage.patientID = patientID;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String urlDB = "jdbc:mysql://localhost:3306/HospitalManagementSystem";
        String username = "root";
        String password = "root@123";
        Connection connection = DriverManager.getConnection(urlDB, username, password);

        if (connection != null) {
            System.out.println("Connection established");
        }
        
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

        signOutButton.addActionListener(e -> {
            this.setVisible(false); // Hide the PatientPage
            LoginFrame loginFrame;
            try {
                loginFrame = new LoginFrame();
                loginFrame.setVisible(true); // Show the LoginFrame
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        // Create appointments button
        JButton appointmentsButton = new JButton("Book an Appointment");
        appointmentsButton.setBounds(20, 50, 200, 30);
        contentPane.add(appointmentsButton);
        appointmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the appointment page
                AppointmentPage appointmentPage;
                try {
                    appointmentPage = new AppointmentPage(patientID);
                    appointmentPage.setVisible(true);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Create pharmacy button next to the appointments button
        JButton pharmacyButton = new JButton("Visit our Store");
        pharmacyButton.setBounds(230, 50, 200, 30); // Position the Pharmacy button next to the Book Appointment button
        contentPane.add(pharmacyButton);

        // Create welcome user label with increased font size and centered
        JLabel welcomeLabel = new JLabel("Welcome " + patientID);
        welcomeLabel.setBounds(20, 90, 200, 20);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size and style
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the label
        contentPane.add(welcomeLabel);

        // Add a label saying "We'd love to hear from you" above the Doctor Name dropdown
        JLabel feedbackPromptLabel = new JLabel("We'd love to hear from you !!");
        feedbackPromptLabel.setBounds(100, 140, 200, 20); // Position the label above the Doctor Name dropdown
        contentPane.add(feedbackPromptLabel);

        // Create a panel for the Doctor Name dropdown and feedback section
        JPanel doctorFeedbackPanel = new JPanel();
        doctorFeedbackPanel.setBounds(20, 130, 350, 200); // Adjust the size of the panel
        doctorFeedbackPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Add a titled border
        doctorFeedbackPanel.setLayout(null); // Use absolute layout

        // Add Doctor Name dropdown
        JLabel doctorNameLabel = new JLabel("Doctor Name:");
        doctorNameLabel.setBounds(50, 50, 100, 20); // Center horizontally and vertically
        doctorFeedbackPanel.add(doctorNameLabel);

        ArrayList<String> doctorNames = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT docName FROM Doctor";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                doctorNames.add(resultSet.getString("docName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        doctorNameDropdown = new JComboBox<>(doctorNames.toArray(new String[0]));
        doctorNameDropdown.setBounds(160, 50, 150, 20); // Center horizontally and vertically
        doctorFeedbackPanel.add(doctorNameDropdown);

        // Add feedback components
        JLabel feedbackLabel = new JLabel("Feedback:");
        feedbackLabel.setBounds(50, 100, 80, 20); // Center horizontally and vertically
        doctorFeedbackPanel.add(feedbackLabel);

        feedbackTextField = new JTextField();
        feedbackTextField.setBounds(160, 100, 150, 20); // Center horizontally and vertically
        doctorFeedbackPanel.add(feedbackTextField);

        JButton submitFeedbackButton = new JButton("Submit Feedback");
        submitFeedbackButton.setBounds(100, 150, 150, 30); // Center horizontally and vertically
        doctorFeedbackPanel.add(submitFeedbackButton);

        submitFeedbackButton.addActionListener(e -> {
            String selectedDoctor = (String) doctorNameDropdown.getSelectedItem();
            String feedbackContent = feedbackTextField.getText();

            try (Statement stmt = connection.createStatement()) {
                // Retrieve the employeeID for the selected doctorName
                String empIdQuery = "SELECT employeeID FROM Doctor WHERE docName = '" + selectedDoctor + "'";
                ResultSet empIdResult = stmt.executeQuery(empIdQuery);
                
                String empId = ""; // Initialize empId
                if (empIdResult.next()) {
                    empId = empIdResult.getString("employeeID");
                }

                // Insert the feedback into the Feedback table
                String insertFeedbackQuery = "INSERT INTO Feedback (patientID, employeeID, review) VALUES ('" + patientID + "', '" + empId + "', '" + feedbackContent + "')";
                stmt.executeUpdate(insertFeedbackQuery);
                
                // Show pop-up message for successful feedback submission
                JOptionPane.showMessageDialog(null, "Feedback submitted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Add the doctorFeedbackPanel to the contentPane
        contentPane.add(doctorFeedbackPanel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PatientPage frame = new PatientPage(patientID);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
