/**
 * Главный класс приложения калькулятора.
 * Инициализирует и запускает пользовательский интерфейс калькулятора.
 */
public class Main {
    /**
     * Точка входа в приложение.
     * Создает экземпляр пользовательского интерфейса и запускает его.
     * 
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        CalculatorUI calculator = new CalculatorUI();
        calculator.run();
    }
}
