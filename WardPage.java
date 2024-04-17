import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WardPage extends JFrame {
    int docNumber = 1;
    private JPanel contentPane;
    private JLabel selectedWardLabel;
    private String selectedWard;
    private static String patientID;
    private int[] lastSelectedBed = {-1}; // Initialize as -1
    private String[] lastSelectedWard = {"None"};


    public WardPage(String patientID) throws SQLException{
        WardPage.patientID = patientID;
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
        WardPage.patientID = patientID;

        setTitle("Ward Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set the background image
                ImageIcon imageIcon = new ImageIcon("ward_background.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Add title label
        JLabel titleLabel = new JLabel("Ward Availability", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font
        titleLabel.setForeground(Color.BLACK); // Set text color
        titleLabel.setOpaque(true); // Ensure label is opaque
        contentPane.add(titleLabel, BorderLayout.NORTH);

        // Create a panel to display ward buttons
        JPanel wardPanel = new JPanel(new GridLayout(0, 1, 5, 5)); // Set vertical and horizontal gaps
        contentPane.add(wardPanel, BorderLayout.WEST);

        // Sample doctor names, replace with your data
        ArrayList<String> doctorNames = new ArrayList<>();
        // int selectedSlot = -1;
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT docName FROM Doctor";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                doctorNames.add(resultSet.getString("docName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (String doctor : doctorNames) {
            JPanel wardButtonPanel = new JPanel(new BorderLayout());
            JButton wardButton = new JButton("Ward" + docNumber);
            wardButton.setPreferredSize(new Dimension(150, 30)); // Set button size
            wardButtonPanel.add(wardButton, BorderLayout.WEST);

            // Create bed buttons
            JPanel bedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Align beds next to each other
            String[] timeSlots = {"5PM - 6PM", "6PM - 7PM", "7PM - 8PM"};
            for (int i = 0; i < timeSlots.length; i++) {
                final int index = i + 1;
                String timeSlot = timeSlots[i];
                JButton bedButton = new JButton(timeSlot);
                bedButton.setOpaque(true);
                bedButton.setBackground(Color.GREEN); // Change background color to green
                bedButton.setForeground(Color.BLACK); // Set text color
                bedButton.setBorderPainted(false);
                bedButton.setPreferredSize(new Dimension(150, 30)); // Set button size
                bedPanel.add(bedButton);
            }
            wardButtonPanel.add(bedPanel, BorderLayout.CENTER);
            wardPanel.add(wardButtonPanel);
            docNumber++;
        }

        // Button to select bed
        JButton selectBedButton = new JButton("Select Bed");
        selectBedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle selecting bed action
                if (lastSelectedBed[0] == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a bed before proceeding.");
                } else {
                    // Use the value of lastSelectedBed here
                    System.out.println("Last selected bed: " + lastSelectedBed[0]);
                    // Proceed with selecting the bed
                    int index = lastSelectedBed[0];
                    selectedWard = lastSelectedWard[0];
                    // You can add your bed selection logic here
                }
            }
        });
        JPanel selectBedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectBedPanel.add(selectBedButton);
        contentPane.add(selectBedPanel, BorderLayout.SOUTH);

        // Create a panel for the back button
        JPanel backButtonPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButtonPanel.add(backButton, BorderLayout.EAST);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                // Open the login page
                new LoginFrame().setVisible(true);
            }
        });
        contentPane.add(backButtonPanel, BorderLayout.SOUTH);
    }

    public WardPage() {
        //TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                WardPage frame = new WardPage(patientID);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
