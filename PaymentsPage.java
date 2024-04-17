import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PaymentsPage extends JFrame {
    private static String adminID;
    public PaymentsPage(String adminID) {
        PaymentsPage.adminID = adminID;
        setTitle("Payments");
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
        String[] columnNames = {"PaymentID", "Payment Amount"};
        Object[][] data = {
            {"PID123", "100.00"},
            {"PID124", "200.00"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Calculate and display the sum of all payments
        double sum = 0;
        for (Object[] row : data) {
            try {
                sum += Double.parseDouble((String) row[1]);
            } catch (NumberFormatException e) {
                // Handle the case where the payment amount is not a valid double
                e.printStackTrace();
            }
        }
        
        // Display the sum at the bottom
        JPanel sumPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sumPanel.add(new JLabel("Total Payments: Rs. " + String.format("%.2f", sum)));
        getContentPane().add(sumPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PaymentsPage frame = new PaymentsPage(adminID);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}