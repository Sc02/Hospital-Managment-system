import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class WardPage extends JFrame {
    private static String adminID;
    public WardPage(String adminID) {
        WardPage.adminID = adminID;
        setTitle("Ward Details");
        setSize(500, 300);
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

        // Table with placeholder data for wards
        String[] columnNames = {"Ward Name", "Ward ID", "Occupied"};
        Object[][] data = {
            {"ICU", "W01", Boolean.FALSE},
            {"MRI room", "W02", Boolean.TRUE},
            {"General Checkup room", "W03", Boolean.TRUE},
            {"Intensive care room", "W04", Boolean.FALSE}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                WardPage frame = new WardPage(adminID);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}