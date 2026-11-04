import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

public final class CalculatorView extends JPanel {
    final JTextField first = new JTextField("12", 12);
    final JTextField second = new JTextField("3", 12);
    final JComboBox<String> operation = new JComboBox<>(new String[]{"+", "−", "×", "÷"});
    final JButton calculate = new JButton("Вычислить");
    final JLabel result = new JLabel("Результат: —");
    final JLabel message = new JLabel("Введите два целых числа и выберите операцию.");
    final JLabel status = new JLabel("Вычислений выполнено: 0");

    public CalculatorView() {
        super(new BorderLayout(12, 16));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel heading = new JPanel(new GridLayout(0, 1, 0, 8));
        heading.add(new JLabel("Калькулятор — обработка исключений"));
        heading.add(new JLabel("Целые числа от −2147483648 до 2147483647."));
        heading.add(new JLabel("При делении дробная часть отбрасывается к нулю: −7 ÷ 2 = −3."));
        add(heading, BorderLayout.NORTH);

        JPanel inputs = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        inputs.add(new JLabel("A:"));
        inputs.add(first);
        inputs.add(operation);
        inputs.add(new JLabel("B:"));
        inputs.add(second);
        inputs.add(calculate);
        add(inputs, BorderLayout.CENTER);

        JPanel output = new JPanel(new GridLayout(0, 1, 0, 10));
        output.add(result);
        output.add(message);
        output.add(status);
        add(output, BorderLayout.SOUTH);
        first.getAccessibleContext().setAccessibleName("Первое число");
        second.getAccessibleContext().setAccessibleName("Второе число");
        operation.getAccessibleContext().setAccessibleName("Операция");
    }

    public void showResult(int value) {
        result.setText("Результат: " + value);
        message.setForeground(new Color(0, 110, 50));
        message.setText("Вычисление выполнено успешно.");
    }

    public void showError(String text) {
        result.setText("Результат: —");
        message.setForeground(new Color(170, 25, 25));
        message.setText(text);
    }
}
