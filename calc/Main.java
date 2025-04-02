package calc;

import java.util.Scanner;

public class Main {
    private static final Calculator calculator = new Calculator();
    private static final ProgrammerCalculator progCalculator = new ProgrammerCalculator();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu();
        
        while (true) {
            try {
                System.out.println("\nВведите операцию (или 'exit' для выхода):");
                String operation = scanner.next().toLowerCase();
                
                if (operation.equals("exit")) {
                    break;
                }
                
                processOperation(operation);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                scanner.nextLine(); // очистка буфера
            }
        }
        
        System.out.println("Программа завершена.");
        scanner.close();
    }
    
    private static void showMenu() {
        System.out.println("Расширенный калькулятор");
        System.out.println("Доступные операции:");
        System.out.println("1. Базовые операции (+, -, *, /)");
        System.out.println("2. Возведение в степень (^)");
        System.out.println("3. Квадратный корень (sqrt)");
        System.out.println("4. Синус (sin)");
        System.out.println("5. Косинус (cos)");
        System.out.println("6. Перевод в двоичную систему (bin)");
        System.out.println("7. Перевод в шестнадцатеричную систему (hex)");
    }
    
    private static void processOperation(String operation) {
        switch (operation) {
            case "+", "-", "*", "/" -> processBasicOperation(operation);
            case "^" -> processPower();
            case "sqrt" -> processSqrt();
            case "sin" -> processSin();
            case "cos" -> processCos();
            case "bin" -> processBinary();
            case "hex" -> processHexadecimal();
            default -> System.out.println("Неизвестная операция!");
        }
    }
    
    private static void processBasicOperation(String operation) {
        System.out.println("Введите первое число:");
        double num1 = scanner.nextDouble();
        System.out.println("Введите второе число:");
        double num2 = scanner.nextDouble();
        
        double result = switch (operation) {
            case "+" -> calculator.add(num1, num2);
            case "-" -> calculator.subtract(num1, num2);
            case "*" -> calculator.multiply(num1, num2);
            case "/" -> calculator.divide(num1, num2);
            default -> 0;
        };
        
        System.out.printf("Результат: %.2f%n", result);
    }
    
    private static void processPower() {
        System.out.println("Введите число:");
        double base = scanner.nextDouble();
        System.out.println("Введите степень:");
        double exponent = scanner.nextDouble();
        System.out.printf("Результат: %.2f%n", calculator.power(base, exponent));
    }
    
    private static void processSqrt() {
        System.out.println("Введите число:");
        double number = scanner.nextDouble();
        System.out.printf("Результат: %.2f%n", calculator.sqrt(number));
    }
    
    private static void processSin() {
        System.out.println("Введите угол в градусах:");
        double degrees = scanner.nextDouble();
        System.out.printf("Результат: %.2f%n", calculator.sin(degrees));
    }
    
    private static void processCos() {
        System.out.println("Введите угол в градусах:");
        double degrees = scanner.nextDouble();
        System.out.printf("Результат: %.2f%n", calculator.cos(degrees));
    }
    
    private static void processBinary() {
        System.out.println("Введите целое число:");
        int number = scanner.nextInt();
        System.out.println("Результат: " + progCalculator.toBinary(number));
    }
    
    private static void processHexadecimal() {
        System.out.println("Введите целое число:");
        int number = scanner.nextInt();
        System.out.println("Результат: " + progCalculator.toHexadecimal(number));
    }
}
