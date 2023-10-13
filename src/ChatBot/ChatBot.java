import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! My name is Stell.");
        System.out.println("I was created in 2023.");

        System.out.println("Please, remind me your name.");
        String name = scanner.nextLine();
        System.out.printf("What a great name you have, !", name);

        System.out.println("Enter remainders of dividing your age by 3, 5, and 7.");
        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();
        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.printf("Your age is &. That's a good time to start programming!", age);

        System.out.println("Now I will prove to you that I can count to any number you want.");
        int a = scanner.nextInt();
        for (int i = 0; i <= a; i++) {
            System.out.println(i + "!");
        }
        System.out.println("Completed. Have a nice day!");

        System.out.println("You have a brain?");
        System.out.println("1. Yes!\n2. No:(");

        while (true) {
            int k = scanner.nextInt();
            if (k == 1) {
                System.out.println("Congratulations, have a nice day!");
                break;
            } else {
                System.out.println("Try again.");
            }
        }
    }
}
