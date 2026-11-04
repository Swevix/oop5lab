import javax.swing.SwingUtilities;

public final class CalculatorTest {
    public static void main(String[] args) throws Exception {
        CalculatorModel model = new CalculatorModel();
        equal(15, model.calculate("12", "3", "+"));
        equal(9, model.calculate("12", "3", "−"));
        equal(36, model.calculate("12", "3", "×"));
        equal(4, model.calculate("12", "3", "÷"));
        equal(-3, model.calculate(" -7 ", "2", "÷"));
        equal(Integer.MIN_VALUE, model.calculate("-2147483648", "1", "÷"));
        for (String input : new String[]{"", " ", "abc", "1.5", "2147483648"}) {
            fails(NumberFormatException.class, () -> model.calculate(input, "1", "+"));
            fails(NumberFormatException.class, () -> model.calculate("1", input, "+"));
        }
        fails(ArithmeticException.class, () -> model.calculate("12", "0", "÷"));
        fails(ArithmeticException.class, () -> model.calculate("2147483647", "1", "+"));
        fails(ArithmeticException.class, () -> model.calculate("-2147483648", "1", "−"));
        fails(ArithmeticException.class, () -> model.calculate("50000", "50000", "×"));
        fails(ArithmeticException.class, () -> model.calculate("-2147483648", "-1", "÷"));
        SwingUtilities.invokeAndWait(() -> {
            CalculatorView view = new CalculatorView();
            new CalculatorController(model, view);
            view.operation.setSelectedItem("÷");
            view.calculate.doClick();
            equal("Результат: 4", view.result.getText());
            view.second.setText("0");
            view.calculate.doClick();
            equal("Результат: —", view.result.getText());
            equal("Деление на ноль невозможно.", view.message.getText());
            view.second.setText("abc");
            view.second.postActionEvent();
            equal("Ошибка ввода: нужны целые числа в диапазоне int.", view.message.getText());
            view.second.setText("2");
            view.first.postActionEvent();
            equal("Результат: 6", view.result.getText());
            equal("Попыток вычисления: 4", view.status.getText());
        });
        System.out.println("All calculator tests passed.");
    }

    private static void equal(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected " + expected + ", got " + actual);
        }
    }

    private static void fails(Class<? extends Exception> type, Runnable action) {
        try {
            action.run();
        } catch (Exception error) {
            if (type.isInstance(error)) return;
            throw new AssertionError("Unexpected exception", error);
        }
        throw new AssertionError("Expected " + type.getSimpleName());
    }
}
