import java.util.Scanner;

public class LoanCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double loan_principal, monthly_payment, interest, annuity_payment;
        int n, years, months;
        double overpayment, total_payment;

        System.out.println(welcome_message);
        System.out.println(calculation_options);

        String calculation_type = scanner.nextLine();

        if (calculation_type.equals("n")) {
            System.out.println(principal_prompt);
            loan_principal = scanner.nextDouble();
            System.out.println(payment_prompt);
            monthly_payment = scanner.nextDouble();
            System.out.println(interest_prompt);
            interest = scanner.nextDouble() / 1200;

            n = (int) Math.ceil(Math.log(monthly_payment / (monthly_payment - interest * loan_principal), 1 + interest));

            if (n > 0) {
                years = n / 12;
                months = n % 12;

                if (years == 0) {
                    System.out.println(year_prompt.formatted(months));
                } else if (months == 0) {
                    System.out.println(mount_prompt.formatted(years));
                } else {
                    System.out.println(m_y_prompt.formatted(years, months));
                }
            } else {
                System.out.println("The loan cannot be repaid with this monthly payment!");
            }

        } else if (calculation_type.equals("a")) {
            System.out.println(principal_prompt);
            loan_principal = scanner.nextDouble();
            System.out.println(periods_prompt);
            n = scanner.nextInt();
            System.out.println(interest_prompt);
            interest = scanner.nextDouble() / 1200;

            annuity_payment = loan_principal * (interest * Math.pow((1 + interest), n)) / (Math.pow((1 + interest), n) - 1);

            years = n / 12;
            months = n % 12;

            if (years == 0) {
                System.out.println(periods_m_y.formatted(Math.round(annuity_payment), months));
            } else if (months == 0) {
                System.out.println(periods_m_y.formatted(Math.round(annuity_payment), years));
            } else {
                System.out.println(periods_m_and_y.formatted(Math.round(annuity_payment), years, months));
            }

        } else if (calculation_type.equals("p")) {
            System.out.println(payment_prompt);
            annuity_payment = scanner.nextDouble();
            System.out.println(periods_numb);
            n = scanner.nextInt();
            System.out.println(interest_prompt);
            interest = scanner.nextDouble() / 1200;

            loan_principal = annuity_payment / ((interest * Math.pow((1 + interest), n)) / (Math.pow((1 + interest), n) - 1));

            years = n / 12;
            months = n % 12;

            if (years == 0) {
                System.out.println(l_principal.formatted(Math.round(loan_principal), months));
            } else if (months == 0) {
                System.out.println(l_principal.formatted(Math.round(loan_principal), years));
            } else {
                System.out.println(principal_m_y.formatted(Math.round(loan_principal), years, months));
            }

        } else if (calculation_type.equals("d")) {
            System.out.println(principal_prompt);
            loan_principal = scanner.nextDouble();
            System.out.println(num_periods);
            n = scanner.nextInt();
            System.out.println(interest_prompt);
            interest = scanner.nextDouble() / 1200;

            total_payment = 0;

            for (int month = 1; month <= n; month++) {
                double differentiated_payment = loan_principal / n + interest * (loan_principal - loan_principal * (month - 1) / n);
                total_payment += differentiated_payment;
                System.out.println(mount_pay.formatted(month, (int) Math.ceil(differentiated_payment)));

                overpayment = total_payment - loan_principal;
                System.out.println(overpayment_result.formatted(Math.round(overpayment)));
            }

        } else {
            System.out.println("Error");
        }
    }
}
