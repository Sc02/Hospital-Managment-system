import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppointmentPage extends JFrame {

    private JPanel contentPane;
    private JLabel selectedDoctorLabel;
    private String selectedDoctor;

    public AppointmentPage() {
        setTitle("Appointments");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set the background image
                ImageIcon imageIcon = new ImageIcon("appointment_background.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Add title label
        JLabel titleLabel = new JLabel("Book Your Appointment Here", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font
        titleLabel.setForeground(Color.BLACK); // Set text color
        titleLabel.setOpaque(true); // Ensure label is opaque
        contentPane.add(titleLabel, BorderLayout.BEFORE_FIRST_LINE);

        // Create a panel to display doctor buttons
        JPanel doctorPanel = new JPanel(new GridLayout(5, 1, 5, 5)); // Set vertical and horizontal gaps
        contentPane.add(doctorPanel, BorderLayout.WEST);

        // Create doctor buttons
        String[] doctors = {"Doctor 1", "Doctor 2", "Doctor 3", "Doctor 4", "Doctor 5"};
        for (String doctor : doctors) {
            JPanel doctorButtonPanel = new JPanel(new BorderLayout());
            JButton doctorButton = new JButton(doctor);
            doctorButton.setPreferredSize(new Dimension(150, 40)); // Set button size
            doctorButtonPanel.add(doctorButton, BorderLayout.WEST);

            // Create time slot buttons
            JPanel timeSlotPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Align time slots next to each other
            String[] timeSlots = {"5PM - 6PM", "6PM - 7PM", "7PM - 8PM"};
            for (String timeSlot : timeSlots) {
                JButton timeSlotButton = new JButton(timeSlot);
                timeSlotButton.setPreferredSize(new Dimension(100, 30)); // Set button size
                timeSlotButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Get the selected doctor and time slot
                        selectedDoctor = doctor;
                        String selectedTimeSlot = timeSlot;
                        // Display confirmation message
                        JOptionPane.showMessageDialog(null, "You selected: " + selectedDoctor + " at " + selectedTimeSlot);
                    }
                });
                timeSlotPanel.add(timeSlotButton);
            }
            doctorButtonPanel.add(timeSlotPanel, BorderLayout.CENTER);
            doctorPanel.add(doctorButtonPanel);
        }

        // Create a label to display selected doctor
        selectedDoctorLabel = new JLabel("Selected Doctor: None");
        contentPane.add(selectedDoctorLabel, BorderLayout.SOUTH);

        // Create a panel for the back button
        JPanel backButtonPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButtonPanel.add(backButton, BorderLayout.EAST);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                // Open the previous page, adjust as necessary
                // For example:
                // new PreviousPage().setVisible(true);
            }
        });
        contentPane.add(backButtonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AppointmentPage frame = new AppointmentPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
