package Calculator;

import java.lang.Math;

public class Calculator implements ICalculator {
    public static int binaryToDecimal(String binary){
        int sum = 0;
        int a = binary.length()-1;
        for(int i = 0; i < binary.length(); i++){
            int number = Integer.parseInt(String.valueOf(binary.charAt(a)));
            sum += (number*(Math.pow(2,i)));
            a--;
        }
        return sum;
    }
    public static String sum(String a, String b) {
        // Declare initial variables to be used later on
        String result = "";
        int carry = 0;
        // Make numbers a & b be the same size
        if (a.length() != b.length()){
            int difference = a.length() - b.length();
            while (difference != 0){
                if (difference > 0){
                    b = "0" + b;
                } else{
                    a = "0" + a;
                }
                difference = a.length() - b.length();
            }
        }
        // Add bits one by one considering carried numbers
        for (int i = a.length()-1; i >= 0; i--) {
            String charA = Character.toString(a.charAt(i));
            String charB = Character.toString(b.charAt(i));
            int added = Integer.parseInt(charA) + Integer.parseInt(charB) + carry;
            carry = 0;
            if (added <= 1){
                result = added + result;
            } else if (added == 2){
                carry = 1;
                result = "0" + result;
            } else if (added == 3) {
                carry = 1;
                result = "1" + result;
            }
        }
        // Add last carried number if it exists
        if (carry == 1){
            result = carry + result;
        }
        return result;
    }
    public static String toHex(String binary) {
        // Turn binary number into decimal
        int decimal = Calculator.binaryToDecimal(binary);
        String hexNum = "";
        if (decimal == 10){
            return "A";
        }else if (decimal == 11){
            return "B";
        }else if (decimal == 12){
            return "C";
        }else if (decimal == 13){
            return "D";
        }else if (decimal == 14){
            return "E";
        }else if (decimal == 15) {
            return "F";
        } else if (decimal < 16) {
            return Integer.toString(decimal);
        }
        // Turn from decimal number to Hexadecimal by using division method
        while(decimal != 0){
            int rest = decimal % 16;
            decimal = (int) Math.floor(decimal / 16);
            // Conditions for numbers > 9
            if (rest == 10){
                hexNum = "A" + hexNum;
            }else if (rest == 11){
                hexNum = "B" + hexNum;
            }else if (rest == 12){
                hexNum = "C" + hexNum;
            }else if (rest == 13){
                hexNum = "D" + hexNum;
            }else if (rest == 14){
                hexNum = "E" + hexNum;
            }else if (rest == 15){
                hexNum = "F" + hexNum;
            }else if (rest == 0) {
                hexNum = 1 + hexNum;
            }else{
                hexNum = rest + hexNum;
            }
        }
        return hexNum;
    }
    public static String fromHex(String hex) {
        //
        int sum = 0;
        int a = hex.length()-1;
        for(int i = 0; i < hex.length(); i++){
            int number = 0;
            if (hex.charAt(a) == 'A'){
                number = 10;
            }else if (hex.charAt(a) == 'B'){
                number = 11;
            }else if (hex.charAt(a) == 'C'){
                number = 12;
            }else if (hex.charAt(a) == 'D'){
                number = 13;
            }else if (hex.charAt(a) == 'E'){
                number = 14;
            }else if (hex.charAt(a) == 'F'){
                number = 15;
            }else{
                number = Integer.parseInt(String.valueOf(hex.charAt(a)));
            }
            sum += (number*(Math.pow(16,i)));
            a--;
        }
        int decimal = sum;
        String binary = "";
        // Decimal to Binary
        while(decimal != 0){
            int rest = decimal % 2;
            decimal = (int) Math.floor(decimal / 2);
            binary = rest + binary;
        }
        return binary;
    }

    public static String sub(String a, String b) {
        // Make numbers be the same size
        if (a.length() != b.length()){
            int difference = a.length() - b.length();
            while (difference != 0){
                if (difference > 0){
                    b = "0" + b;
                } else{
                    a = "0" + a;
                }
                difference = a.length() - b.length();
            }
        }
        // Complemento a la base
        char[] bArray = b.toCharArray();
        for(int i = 0; i < bArray.length; i++) {
            if(bArray[i] == '0') {
                bArray[i] = '1';
            } else {
                bArray[i] = '0';
            }
        }
        String comp = new String(bArray);
        String compB = Calculator.sum(comp, "1");
        String aMinusB = Calculator.sum(a, compB);
        if(aMinusB.length() > a.length() && aMinusB.charAt(0) == '1'){
            return aMinusB.substring(1);
        }
        return aMinusB;
    }

    @Override
    public String mult(String a, String b) {
        return null;
    }
    @Override
    public String div(String a, String b) {
        return null;
    }
}
