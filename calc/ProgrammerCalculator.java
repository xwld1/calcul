package calc;

public class ProgrammerCalculator {
    public String toBinary(int number) {
        return Integer.toBinaryString(number);
    }
    
    public String toHexadecimal(int number) {
        return Integer.toHexString(number).toUpperCase();
    }
} 