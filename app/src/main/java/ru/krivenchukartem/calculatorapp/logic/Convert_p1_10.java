package ru.krivenchukartem.calculatorapp.logic;


import ru.krivenchukartem.calculatorapp.data.DataSource;

public class Convert_p1_10 {
    private static double charToNum(String ch){
        return DataSource.INSTANCE.getStringToDigit().get(ch);
    }

    public static double solve(String P_num, int P){
        boolean isNegative = false;
        if (P_num.startsWith("-")){
            isNegative = true;
            P_num = P_num.substring(1);
        }

        double result = 0d;
        int dotPosition = P_num.indexOf("."); //123.456
        int startWeight;

        if (dotPosition > 0){
            startWeight = dotPosition - 1;
        }
        else{
            startWeight = P_num.length() - 1;
        }

        P_num = P_num.replace(".", "");

        for (int i = 0; i < P_num.length(); i++){
            result += dval(String.valueOf(P_num.charAt(i)), P, startWeight - i);
        }

        if (isNegative){
            result *= -1;
        }
        return result;
    }

    public static double dval(String P_num, int P, int weight){
        return charToNum(P_num) * Math.pow(P, weight);
    }
}
