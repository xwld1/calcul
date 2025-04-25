package calc;

/**
 * Абстрактный класс, определяющий базовые операции калькулятора
 */
public abstract class BaseCalculator {
    public abstract double add(double a, double b);
    public abstract double subtract(double a, double b);
    public abstract double multiply(double a, double b);
    public abstract double divide(double a, double b) throws ArithmeticException;
    public abstract double power(double base, double exponent);
    public abstract double sqrt(double number) throws ArithmeticException;
} 