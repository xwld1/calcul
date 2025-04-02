package calc;

public class Calculator {
    public double add(double a, double b) {
        return a + b;
    }
    
    public double subtract(double a, double b) {
        return a - b;
    }
    
    public double multiply(double a, double b) {
        return a * b;
    }
    
    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль!");
        }
        return a / b;
    }
    
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
    
    public double sqrt(double number) throws ArithmeticException {
        if (number < 0) {
            throw new ArithmeticException("Невозможно извлечь корень из отрицательного числа!");
        }
        return Math.sqrt(number);
    }
    
    public double sin(double degrees) {
        return Math.sin(Math.toRadians(degrees));
    }
    
    public double cos(double degrees) {
        return Math.cos(Math.toRadians(degrees));
    }
} 