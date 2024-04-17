import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminAppointments extends JFrame {
    private static String adminID;
    public AdminAppointments(String adminID) {
        AdminAppointments.adminID = adminID;
        setTitle("List of All Appointments");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Navigation bar with Back button
        JPanel navBarPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close this window
                try {
                    new Admin(adminID).setVisible(true);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } // Open the Admin page
            }
        });
        navBarPanel.add(backButton, BorderLayout.EAST);
        getContentPane().add(navBarPanel, BorderLayout.NORTH);

        // Table with placeholder data
        String[] columnNames = {"Doctor", "Patient", "Date-Time", "Ward"};
        Object[][] data = {
            {"Dr. Smith", "John Doe", "2023-10-05 14:00", "Ward 1"},
            {"Dr. Jones", "Jane Doe", "2023-10-06 16:00", "Ward 2"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminAppointments frame = new AdminAppointments(adminID);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}