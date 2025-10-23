import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StudentRegistrationForm {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentRegistrationForm::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Student Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        JTextField name = new JTextField();
        JTextField roll = new JTextField();
        JTextField course = new JTextField();

        form.add(new JLabel("Name:")); form.add(name);
        form.add(new JLabel("Roll No:")); form.add(roll);
        form.add(new JLabel("Course:")); form.add(course);

        JTextArea output = new JTextArea();
        output.setEditable(false);

        JButton submit = new JButton("Submit");
        submit.addActionListener((ActionEvent e) -> {
            String n = name.getText().trim();
            String r = roll.getText().trim();
            String c = course.getText().trim();
            if (n.isEmpty() || r.isEmpty() || c.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields required", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            output.setText("Student Registered:\nName: " + n + "\nRoll: " + r + "\nCourse: " + c);
        });

        frame.add(form, BorderLayout.NORTH);
        frame.add(submit, BorderLayout.CENTER);
        frame.add(new JScrollPane(output), BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
