package ru.krivenchukartem.calculatorapp.logic;

import ru.krivenchukartem.calculatorapp.data.DataSource;

public class Convert_10_p1 {
    public static String solve(double n, int p, int c){
        boolean isNegative = Math.abs(n) != n;
        if (isNegative) n = Math.abs(n);
        int int_part = (int) Math.round(n);
        double double_part = n - int_part;

        String converted_number = format(intToP(int_part, p), fltToP(double_part, p, c));

        if (isNegative){
            converted_number = "-" + converted_number;
        }

        return converted_number;
    }

    private static String format(String wholePart, String fractionPart){
        boolean isFractionPartEqualZero = true;
        for (int i = 0; i < fractionPart.length(); i++){
            if (fractionPart.charAt(i) != '0'){
                isFractionPartEqualZero = false;
                break;
            }
        }
        if (isFractionPartEqualZero){
            return wholePart;
        }
        else{
            return String.join(".", wholePart, fractionPart);
        }
    }

    private static String intToString(int n){
        return DataSource.INSTANCE.getDigitToString().get(n);
    }

    public static String intToP(int n, int p){
        String result = "";
        String rest = "";
        while (n > 0){
            rest = intToString(n % p);
            result = rest + result;
            n = n / p;

        }
        return result;
    }

    public static String fltToP(double n, int p, int c){
        String result = "";
        while (c > 0){
            n = n * p;
            String[] arr = String.valueOf(n).split("\\.");
            n = Double.parseDouble("0." + arr[1]);
            result = result + intToString(Integer.parseInt(arr[0]));
            c--;
        }
        return result;
    }
}
