package calc;

import java.util.Scanner;

public class Main {
    private static final Calculator calculator = new Calculator();
    private static final ProgrammerCalculator progCalculator = new ProgrammerCalculator();
    private static final Scanner scanner = new Scanner(System.in);
    private static String numSystem = "decimal"; // По умолчанию десятичная

    public static void main(String[] args) {
        chooseNumberSystem();
        showMenu();
        
        while (true) {
            try {
                System.out.println("\nВведите операцию (или 'exit' для выхода, 'system' для смены системы счисления):");
                String operation = scanner.next().toLowerCase();
                
                if (operation.equals("exit")) {
                    break;
                }
                
                if (operation.equals("system")) {
                    chooseNumberSystem();
                    showMenu();
                    continue;
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
    
    private static void chooseNumberSystem() {
        System.out.println("Выберите систему счисления:");
        System.out.println("1. Двоичная");
        System.out.println("2. Десятичная");
        System.out.println("3. Шестнадцатеричная");
        
        int choice = 0;
        while (choice < 1 || choice > 3) {
            try {
                // Используем обычное чтение числа, не зависящее от выбранной системы
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1 -> numSystem = "binary";
                        case 2 -> numSystem = "decimal";
                        case 3 -> numSystem = "hex";
                        default -> {
                            System.out.println("Выберите число от 1 до 3");
                            choice = 0;
                        }
                    }
                } else {
                    System.out.println("Введите число от 1 до 3");
                    scanner.next(); // очистка некорректного ввода
                }
            } catch (Exception e) {
                System.out.println("Введите корректное число");
                scanner.nextLine();
            }
        }
        System.out.println("Выбрана " + getSystemName(numSystem) + " система счисления");
    }
    
    private static String getSystemName(String system) {
        return switch (system) {
            case "binary" -> "двоичная";
            case "hex" -> "шестнадцатеричная";
            default -> "десятичная";
        };
    }
    
    private static void showMenu() {
        System.out.println("\nРасширенный калькулятор - " + getSystemName(numSystem) + " система");
        System.out.println("Доступные операции:");
        
        switch (numSystem) {
            case "binary" -> {
                System.out.println("1. Базовые операции:");
                System.out.println("   + (сложение)");
                System.out.println("   - (вычитание)");
                System.out.println("   * (умножение)");
                System.out.println("   / (деление, результат округляется до целого)");
                System.out.println("2. ^ (возведение в степень)");
                System.out.println("3. sqrt (квадратный корень, результат округляется)");
                System.out.println("Для ввода используйте двоичные числа (0 и 1)");
            }
            case "hex" -> {
                System.out.println("1. Базовые операции:");
                System.out.println("   + (сложение)");
                System.out.println("   - (вычитание)");
                System.out.println("   * (умножение)");
                System.out.println("   / (деление, результат округляется до целого)");
                System.out.println("2. ^ (возведение в степень)");
                System.out.println("3. sqrt (квадратный корень, результат округляется)");
                System.out.println("Для ввода используйте шестнадцатеричные числа (0-9, A-F)");
            }
            default -> {
                System.out.println("1. Базовые операции:");
                System.out.println("   + (сложение)");
                System.out.println("   - (вычитание)");
                System.out.println("   * (умножение)");
                System.out.println("   / (деление)");
                System.out.println("2. ^ (возведение в степень)");
                System.out.println("3. sqrt (квадратный корень)");
                System.out.println("4. sin (синус угла в градусах)");
                System.out.println("5. cos (косинус угла в градусах)");
            }
        }
        
     
    }
    
    private static void processOperation(String operation) {
        switch (operation) {
            case "+", "-", "*", "/" -> processBasicOperation(operation);
            case "^" -> processPower();
            case "sqrt" -> processSqrt();
            case "sin" -> {
                if (numSystem.equals("decimal")) {
                    processSin();
                } else {
                    System.out.println("Операция доступна только в десятичной системе");
                }
            }
            case "cos" -> {
                if (numSystem.equals("decimal")) {
                    processCos();
                } else {
                    System.out.println("Операция доступна только в десятичной системе");
                }
            }
            default -> System.out.println("Неизвестная операция!");
        }
    }
    
    private static double getValidNumber() {
        while (true) {
            if (numSystem.equals("decimal") && scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else if (numSystem.equals("binary") && scanner.hasNext()) {
                String input = scanner.next();
                try {
                    return Integer.parseInt(input, 2);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите корректное двоичное число (0 и 1).");
                    continue;
                }
            } else if (numSystem.equals("hex") && scanner.hasNext()) {
                String input = scanner.next();
                try {
                    return Integer.parseInt(input, 16);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите корректное шестнадцатеричное число (0-9, A-F).");
                    continue;
                }
            } else {
                System.out.println("Ошибка: введите корректное число для текущей системы счисления.");
                scanner.next(); // очистка некорректного ввода
            }
        }
    }
    
    private static void processBasicOperation(String operation) {
        System.out.println("Введите первое число:");
        double num1 = getValidNumber();
        System.out.println("Введите второе число:");
        double num2 = getValidNumber();
        
        double result = switch (operation) {
            case "+" -> calculator.add(num1, num2);
            case "-" -> calculator.subtract(num1, num2);
            case "*" -> calculator.multiply(num1, num2);
            case "/" -> calculator.divide(num1, num2);
            default -> 0;
        };
        
        displayResult(result);
    }
    
    private static void processPower() {
        System.out.println("Введите число:");
        double base = getValidNumber();
        System.out.println("Введите степень:");
        double exponent = getValidNumber();
        double result = calculator.power(base, exponent);
        displayResult(result);
    }
    
    private static void processSqrt() {
        System.out.println("Введите число:");
        double number = getValidNumber();
        double result = calculator.sqrt(number);
        displayResult(result);
    }
    
    private static void processSin() {
        System.out.println("Введите угол в градусах:");
        double degrees = getValidNumber();
        double result = calculator.sin(degrees);
        displayResult(result);
    }
    
    private static void processCos() {
        System.out.println("Введите угол в градусах:");
        double degrees = getValidNumber();
        double result = calculator.cos(degrees);
        displayResult(result);
    }
    
    private static void displayResult(double result) {
        switch (numSystem) {
            case "binary" -> System.out.println("Результат: " + Integer.toBinaryString((int) result));
            case "hex" -> System.out.println("Результат: " + Integer.toHexString((int) result));
            default -> System.out.printf("Результат: %.2f%n", result);
        }
    }
}
