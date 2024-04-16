import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PharmacyPage extends JFrame {
    
    private JPanel contentPane;
    private LoginFrame loginFrame; 
    private JLabel totalCostLabel;
    private double totalCost = 0.0;

    // Assuming these are the costs for the medicines
    private double[] medicineCosts = {10.0, 20.0, 15.0, 25.0, 30.0};


    public PharmacyPage(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;

        setTitle("Pharmacy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Adjust the path to your background image as necessary
                g.drawImage(new ImageIcon("pharmacy_background.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        contentPane.setOpaque(false);
        //beggining of med display
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // 5 rows for medicines
        contentPane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setOpaque(false);

        // Create and add 5 medicine lines with increment counters
        for (int i = 1; i <= 5; i++) {
            JPanel medicinePanel = new JPanel();
            medicinePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            medicinePanel.setOpaque(false);
            int j = i-1;
            JLabel medicineLabel = new JLabel("Medicine" + i + " (Rs. " + medicineCosts[i-1] + ")");
            JTextField countField = new JTextField("0", 5);
            countField.setEditable(false);

            JButton incrementButton = new JButton("+");
            incrementButton.addActionListener(e -> {
                int count = Integer.parseInt(countField.getText());
                countField.setText(String.valueOf(++count));
                updateTotalCost(medicineCosts[j], true);
            });

            JButton decrementButton = new JButton("-");
            decrementButton.addActionListener(e -> {
                int count = Integer.parseInt(countField.getText());
                if (count > 0) {
                    countField.setText(String.valueOf(--count));
                    updateTotalCost(medicineCosts[j], false);
                }
            });

            medicinePanel.add(medicineLabel);
            medicinePanel.add(decrementButton);
            medicinePanel.add(countField);
            medicinePanel.add(incrementButton);

            centerPanel.add(medicinePanel);
        }
        // Total cost label
        totalCostLabel = new JLabel("Total Cost: Rs. 0.0");
        centerPanel.add(totalCostLabel);

        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(e -> {
            // Implement what happens when the Buy button is clicked
            JOptionPane.showMessageDialog(this, "Purchase made!");
        });
        contentPane.add(buyButton, BorderLayout.SOUTH);
        //end of med display

        // Navigation bar panel
        JPanel navBarPanel = new JPanel(new BorderLayout());
        contentPane.add(navBarPanel, BorderLayout.NORTH);

        // Heading label
        JLabel headingLabel = new JLabel("Pharmacy");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        navBarPanel.add(headingLabel, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Here, you can define what happens when the "Back" button is clicked.
                // For example, you might want to close this window and show the previous one.
                dispose(); // Close the current window
                // new PreviousPage().setVisible(true); // Open the previous page, adjust as necessary
            }
        });

        // Back button
        
        backButton.addActionListener(e -> {
            this.setVisible(false); // Hide PharmacyPage
            loginFrame.setVisible(true); // Show LoginFrame
        });
        navBarPanel.add(backButton, BorderLayout.EAST);

        // Adjustments to ensure the nav bar and heading look good
        navBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void updateTotalCost(double cost, boolean increment) {
        if (increment) {
            totalCost += cost;
        } else {
            totalCost -= cost;
        }
        totalCostLabel.setText("Total Cost: Rs." + String.format("%.2f", totalCost));
    }

    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        EventQueue.invokeLater(() -> {
            try {
                PharmacyPage frame = new PharmacyPage(loginFrame);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}