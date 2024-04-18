import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Employee extends JFrame {

    public Employee(String adminID) {
        setTitle("Employee Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        // Navigation Bar
        JPanel navBar = new JPanel();
        navBar.setBackground(Color.LIGHT_GRAY);
        navBar.setLayout(new BorderLayout());
        JButton signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add sign out functionality here
                dispose(); // Close the current window
                // Add code to sign out
            }
        });
        navBar.add(signOutButton, BorderLayout.EAST);

        contentPane.add(navBar, BorderLayout.NORTH);

        // Add Employee Label
        JLabel addEmployeeLabel = new JLabel("Add Employee:");
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(80, 30));

        // Text Boxes
        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new FlowLayout());
        JTextField textField1 = new JTextField("Placeholder 1");
        JTextField textField2 = new JTextField("Placeholder 2");
        JTextField textField3 = new JTextField("Placeholder 3");
        JTextField textField4 = new JTextField("Placeholder 4");
        JTextField textField5 = new JTextField("Placeholder 5");
        JTextField textField6 = new JTextField("Placeholder 6");
        JTextField textField7 = new JTextField("Placeholder 7");
        textFieldsPanel.add(addEmployeeLabel);
        textFieldsPanel.add(textField1);
        textFieldsPanel.add(textField2);
        textFieldsPanel.add(textField3);
        textFieldsPanel.add(textField4);
        textFieldsPanel.add(textField5);
        textFieldsPanel.add(textField6);
        textFieldsPanel.add(textField7);
        textFieldsPanel.add(addButton);

        contentPane.add(textFieldsPanel, FlowLayout.CENTER);

        JPanel deleteDoctorPanel = new JPanel();
        deleteDoctorPanel.setLayout(new FlowLayout());
        JLabel deleteEmployeeLabel = new JLabel("Delete Employee:");
        JButton deleteButton = new JButton("Delete");
        addButton.setPreferredSize(new Dimension(80, 30));
        JTextField textField8 = new JTextField("Placeholder 8");
        deleteDoctorPanel.add(deleteEmployeeLabel);
        deleteDoctorPanel.add(textField8);
        deleteDoctorPanel.add(deleteButton);
        contentPane.add(deleteDoctorPanel, FlowLayout.CENTER);
        // Add Button
        // Set button size

        setContentPane(contentPane);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Employee("admin123").setVisible(true);
        });
    }
}