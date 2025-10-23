import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CalculatorApp {
    private JTextField display;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorApp().createAndShow());
    }

    private void createAndShow() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] keys = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
        for (String k : keys) {
            JButton b = new JButton(k);
            b.addActionListener(this::onKey);
            buttons.add(b);
        }

        frame.add(buttons, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void onKey(ActionEvent e) {
        String cmd = ((JButton)e.getSource()).getText();
        if ("=".equals(cmd)) {
            try {
                double result = eval(display.getText());
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
            return;
        }
        display.setText(display.getText() + cmd);
    }

    // Very small expression evaluator supporting + - * /
    private double eval(String expr) {
        return new Object() {
            int pos = -1, ch;
            void nextChar() { ch = (++pos < expr.length()) ? expr.charAt(pos) : -1; }
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expr.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }
            double parseExpression() {
                double x = parseTerm();
                while (true) {
                    if (ch == '+') { nextChar(); x += parseTerm(); }
                    else if (ch == '-') { nextChar(); x -= parseTerm(); }
                    else return x;
                }
            }
            double parseTerm() {
                double x = parseFactor();
                while (true) {
                    if (ch == '*') { nextChar(); x *= parseFactor(); }
                    else if (ch == '/') { nextChar(); x /= parseFactor(); }
                    else return x;
                }
            }
            double parseFactor() {
                StringBuilder sb = new StringBuilder();
                while (ch >= '0' && ch <= '9' || ch == '.') { sb.append((char)ch); nextChar(); }
                if (sb.length() == 0) throw new RuntimeException("Number expected");
                return Double.parseDouble(sb.toString());
            }
        }.parse();
    }
}
