public final class CalculatorModel {
    public int calculate(String first, String second, String operation) {
        int a = Integer.parseInt(first.trim());
        int b = Integer.parseInt(second.trim());
        switch (operation) {
            case "+": return Math.addExact(a, b);
            case "−": return Math.subtractExact(a, b);
            case "×": return Math.multiplyExact(a, b);
            case "÷":
                if (b == 0) {
                    throw new ArithmeticException("Деление на ноль невозможно.");
                }
                if (a == Integer.MIN_VALUE && b == -1) {
                    throw new ArithmeticException("Результат выходит за диапазон int.");
                }
                return a / b;
            default: throw new IllegalArgumentException("Неизвестная операция.");
        }
    }
}
