import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArithmeticCalculatorGUI {

    private JFrame frame;
    private JTextField textField;
    private double num1 = 0, num2 = 0, result = 0;
    private String operator = "";

    // Main method to launch the GUI application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ArithmeticCalculatorGUI window = new ArithmeticCalculatorGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor to initialize the components and GUI
    public ArithmeticCalculatorGUI() {
        // Initialize the frame
        frame = new JFrame("Arithmetic Calculator");
        frame.setBounds(100, 100, 400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Initialize the text field to display numbers and results (larger size and styling)
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 36));  // Glorified font size for result
        textField.setBounds(10, 10, 360, 80); // Larger height for result area
        textField.setColumns(10);
        textField.setHorizontalAlignment(JTextField.RIGHT);  // Align text to the right for better clarity
        textField.setBackground(new Color(230, 230, 250)); // Light lavender background color for the text field
        textField.setForeground(new Color(0, 0, 0)); // Black text for better contrast
        textField.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));  // Blue border to glorify
        frame.getContentPane().add(textField, BorderLayout.NORTH);

        // Create a panel for the calculator buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5)); // 4 rows, 4 columns, 5px padding between buttons

        // Create number buttons (0-9) and operator buttons (+, -, *, /, =)
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        // Add buttons to the panel with action listeners
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Tahoma", Font.PLAIN, 14));  // Smaller font size for buttons
            button.setPreferredSize(new Dimension(40, 40));  // Set all buttons to a smaller size (40x40)
            button.setBackground(new Color(240, 240, 240));  // Light gray background for buttons
            button.setForeground(new Color(0, 0, 0)); // Black text for buttons
            button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1)); // Gray border for buttons
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(e);
                }
            });
            panel.add(button);
        }

        // Add the panel with buttons to the frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    // Handle button clicks for numbers and operations
    private void handleButtonClick(ActionEvent e) {
        String command = e.getActionCommand();

        // If the button is a number or decimal point, update the text field
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.charAt(0) == '.') {
            textField.setText(textField.getText() + command);
        }
        // If the button is an operator (+, -, *, /)
        else if (command.equals("=")) {
            // Perform calculation when "=" is pressed
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(frame, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            // Display the result
            textField.setText(String.valueOf(result));
            operator = "";  // Clear operator for the next calculation
        } else {
            // Store the operator and the first number
            if (!textField.getText().equals("")) {
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
                operator = command;
            }
        }
    }
}


