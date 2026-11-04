public final class CalculatorController {
    private final CalculatorModel model;
    private final CalculatorView view;
    private int attempts;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        view.calculate.addActionListener(event -> calculate());
        view.first.addActionListener(event -> calculate());
        view.second.addActionListener(event -> calculate());
    }

    private void calculate() {
        try {
            int value = model.calculate(view.first.getText(), view.second.getText(),
                    (String) view.operation.getSelectedItem());
            view.showResult(value);
        } catch (NumberFormatException error) {
            view.showError("Ошибка ввода: нужны целые числа в диапазоне int.");
        } catch (ArithmeticException error) {
            view.showError("÷".equals(view.operation.getSelectedItem())
                    ? error.getMessage()
                    : "Переполнение: результат выходит за диапазон int.");
        } catch (IllegalArgumentException error) {
            view.showError(error.getMessage());
        } finally {
            attempts++;
            view.status.setText("Попыток вычисления: " + attempts);
        }
    }
}
