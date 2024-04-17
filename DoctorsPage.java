import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DoctorsPage extends JFrame {
    private static String adminID;
    public DoctorsPage(String adminID) {
        DoctorsPage.adminID = adminID;
        setTitle("Doctors");
        setSize(400, 300);
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

        // Table with placeholder data for payments
        String[] columnNames = {"DoctorID", "Doctor name","Salary"};
        Object[][] data = {
            {"d001", "Mukesh",1000000},
            {"d002", "Gukesh",7500000}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DoctorsPage frame = new DoctorsPage(adminID);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}