package honestcalculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    Validator validator = new Validator();
    Requester requester = new Requester();
    static Map<String, Double> memory = new HashMap<>();

    public static void main(String[] args) {
        new Main().runProgram();
    }

    void runProgram() {

        while (true) {
            String[] calcArr = requester.equationRequest();

            if (validator.isValidNumberType(calcArr) && validator.isValidExpression(calcArr)) {
                LazinessEvaluator lazinessEvaluator = new LazinessEvaluator();
                lazinessEvaluator.determineLaziness(calcArr);

                double ans = calculate(calcArr);
                if (validator.isDivideByZero(ans)) {
                    continue;
                }
                printAns(ans);

                if (requester.willStoreRequest() && requester.insistsOnStore(ans)) {
                        memory.put("M", ans);
                }

                if (!requester.continueProgramRequest()) {
                    return;
                }
            }
        }
    }

    static Double getDoubleVal(String val) {
        if (val.equals("M") ) {
            return memory.getOrDefault("M", (double) 0);
        }
        return Double.parseDouble(val);
    }

    double calculate(String[] split) {
        double ans = 0f;
        String sign = "";

        for (int i = 0; i < split.length; i++) {
            if (split[i].matches("[-+/*]")) {
                sign = split[i];
            } else {
                double num = getDoubleVal(split[i]);
                if (i == 0) {
                    ans = num;
                }
                switch (sign) {
                    case "+" -> ans += num;
                    case "-" -> ans -= num;
                    case "*" -> ans *= num;
                    case "/" -> ans /= num;
                }
            }
        }
        return ans;
    }

    void printAns(double ans) {

        if ( (long) ans == ans){
            String f = (long) ans + ".0";
            System.out.println(f);
        }else {
            System.out.println(ans);
        }
    }
}

class Validator {

    String[] sarcasticMsg = {
            "Do you even know what numbers are? Stay focused!",
            "Yes ... an interesting math operation. You've slept through all classes, haven't you?",
            "Yeah... division by zero. Smart move..."
    };

    boolean isValidNumberType(String[] split) {

        for (int i = 0; i < split.length; i++) {

           if (i % 2 == 0 && split[i].matches("[+-]?([0-9]*[.])?[0-9]+")) {
                try {
                    Float.parseFloat(split[i]);
                } catch (RuntimeException ignore) {
                    return false;
                }
            }else if (i % 2 == 0 && !split[i].matches("M")) {
               System.out.println(sarcasticMsg[0]);
               return false;
           }
        }
        return true;
    }

    boolean isValidExpression(String[] split) {
        if (split.length < 3) {
            return false;
        }

        if (!split[1].matches("[-+*/]")) {
            System.out.println(sarcasticMsg[1]);
            return false;
        }
        return true;
    }

    boolean isDivideByZero(double ans) {
        if (Double.isInfinite(ans)) {
            System.out.println(sarcasticMsg[2]);
            return true;
        }

        return false;
    }
}

class Requester {

    Scanner in = new Scanner(System.in);

    String[] msg = {
            "Enter an equation",
            "Do you want to store the result? (y / n):",
            "Do you want to continue calculations? (y / n):"
    };

    String[] equationRequest() {
        System.out.println(msg[0]);
        String calc = in.nextLine();

        return calc.split(" ");
    }

    boolean willStoreRequest() {

        while (true) {
            System.out.println(msg[1]);
            String willStoreRes = in.nextLine();

            switch (willStoreRes) {
                case "y" -> {
                    return true;
                }
                case "n" -> {
                    return false;
                }
                default -> System.out.println("No such request available.");
            }
        }
    }

    String[] checkStoreMsg = {
            "Are you sure? It is only one digit! (y / n)",
            "Don't be silly! It's just one number! Add to the memory? (y / n)",
            "Last chance! Do you really want to embarrass yourself? (y / n)"
    };

    boolean insistsOnStore(double ans){
        if (LazinessEvaluator.isOneDigit(ans)){
            System.out.println(checkStoreMsg[0]);
            String storeAns = in.nextLine();
            int i = 1;
            while(storeAns.equals("y") && i < checkStoreMsg.length){
                System.out.println(checkStoreMsg[i]);
                i++;
                storeAns = in.nextLine();
            }
            if (storeAns.equals("y")){
                return true;
            }
        }else{
            return true;
        }
        return false;
    }

    boolean continueProgramRequest() {

        while(true) {
            System.out.println(msg[2]);
            String willContinueCalc = in.nextLine();
            switch (willContinueCalc) {
                case "y" -> {
                    return true;
                }
                case "n" -> {
                    in.close();
                    return false;
                }
                default -> System.out.println("No such request available.");
            }
        }
    }
}

class LazinessEvaluator{

    String[] lazyMessages = {
            " ... lazy",
            " ... very lazy",
            " ... very, very lazy",
            "You are"
    };

    void determineLaziness(String[] calc){
        double op1 = Main.getDoubleVal(calc[0]);
        double op2 = Main.getDoubleVal(calc[2]);
        String operator = calc[1];

        StringBuilder lazy = new StringBuilder();

        if (isOneDigit(op1) && isOneDigit(op2)){
            lazy.append(lazyMessages[0]);
        }
        if( (op1 == 1 || op2 == 1) && operator.equals("*")){
            lazy.append(lazyMessages[1]);
        }
        if( (op1 == 0 || op2 == 0) &&
                operator.matches("[-+*]")){
            lazy.append(lazyMessages[2]);
        }

        if (!lazy.isEmpty()){
            String lazyMessage = lazyMessages[3] + lazy;
            System.out.println(lazyMessage);
        }
    }

    static boolean isOneDigit(double operand){
        return operand > -10 && operand < 10 && operand == (long) operand;
    }

}
