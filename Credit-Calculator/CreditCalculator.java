import java.util.Scanner;

public class CreditCalculator {
    public static double A = 0, P = 0, n = 0, i = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Що ви хочете розрахувати? \n" +
                    "введіть \"1\" для кількості щомісячних платежів,\n" +
                    "введіть \"2\" для щомісячної суми аннуїтетного платежу,\n" +
                    "введіть \"3\" для основної суми кредиту:");

            try{
                String mode = in.next();
                if(mode.equals("n") || mode.equals("N")){
                    writeData(false,  false,  true);
                    n = absoluteMonth(numberOfPayments(A, P, i));
                    if((n / 12) == 0){
                        System.out.println("На погашення цього кредиту знадобиться " + (int)n + " місяців! ");
                    }
                    else if((n % 12) == 0){
                        System.out.println("На погашення цього кредиту знадобиться " + (int)(n / 12) + " років! ");
                    }
                    else{
                        System.out.println("На погашення цього кредиту знадобиться " + (int)(n / 12) + " років і " + (int)(n % 12) + " місяців! ");
                    }
                }
                else if(mode.equals("1") || mode.equals("1")){
                    writeData(true,  false,  false);
                    A = absoluteMonth(annualPayment(P, n, i));
                    System.out.println("Ваш щомісячний платіж = " + (int)A + "! ");
                }
                else if(mode.equals("2") || mode.equals("2")){
                    writeData(false,  true, false);
                    P = (int)principalLoanAmount(A, n, i);
                    System.out.println("Ваш основний кредит = " + (int)P +"! ");
                }
                else if(mode.equals("3") || mode.equals("3")){//тест функції відмінних платежів
                    writeData(true,  false,  false);
                    int overPayment = 0;
                    for (int m = 1; m <= n; m++) {
                        int D = absoluteMonth(differentiatedPayment(P, n, i, m));
                        System.out.println("Місяць " + m + ": платіж становить " + D);
                        overPayment += D;
                    }
                    System.out.println("Переплата = " + (int)(overPayment - P));
                }
            }
            catch(Exception e){
                System.out.println("Невірні параметри ");
            }
            System.out.println("-------------------------------------------------");
        }
    }

    private static void writeData(boolean _A, boolean _P, boolean _n){
        Scanner in = new Scanner(System.in);
        if(!_P){
            System.out.println("Введіть основну суму кредиту: ");
            try {
                P = in.nextDouble();
            } catch (Exception e) {
                System.out.println("Невірні параметри ");
            }
        }
        if(!_A){
            System.out.println("Введіть щомісячний платіж: ");
            try {
                A = in.nextDouble();
            } catch (Exception e) {
                System.out.println("Невірні параметри ");
            }
        }
        if(!_n){
            System.out.println("Введіть кількість періодів: ");
            try {
                n = in.nextDouble();
            } catch (Exception e) {
                System.out.println("Невірні параметри ");
            }
        }
        System.out.println("Введіть відсоток кредиту: ");
        try {
            i = in.nextDouble();
        } catch (Exception e) {
            System.out.println("Невірні параметри ");
        }
    }

    private static double annualPayment(double P, double n, double i){
        i = procent(i);
        return (P * ((i * Math.pow(1 + i, n)) / (Math.pow(1 + i, n) - 1)));
    }
    private static double principalLoanAmount(double A, double n, double i){
        i = procent(i);
        return A / ((i * Math.pow(1 + i, n)) / (Math.pow(1 + i, n) - 1));
    }
    private static double numberOfPayments(double A, double P, double i){
        i = procent(i);
        return log(1 + i, (A / (A - (i * P))));
    }
    private static double differentiatedPayment(double P, double n, double i, double m){
        i = procent(i);
        return (P / n) + (i * (P - ((P * (m - 1)) / n)));
    }

    private static double log(double base, double x){
        return Math.log(x) / Math.log(base);
    }
    private static double procent(double value){
        return (value / (1200));
    }

    private static int absoluteMonth(double month){
        return (month - (int)month) > 0.0? (int)month + 1: (int)month;
    }
}
