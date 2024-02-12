package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {
    private int grn = 1100;
    private int water = 1000;
    private int milk = 540;
    private int coffee = 120;
    private int cups = 9;
    private int counter = 0;
    private String status = "wait";

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Write action (buy, fill, take, remaining, exit):\n");
            String action = scanner.next();
            coffeeMachine.action(action);

            if (action.equals("buy")) {
                System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 4 - back for menu:\n");
                String typeOfCoffee = scanner.next();
                coffeeMachine.action(typeOfCoffee);
            } else if (action.equals("fill")) {
                System.out.print("Write how many ml of water you want to add:\n");
                String water = scanner.next();
                coffeeMachine.action(water);
                System.out.print("Write how many ml of milk you want to add:\n");
                String milk = scanner.next();
                coffeeMachine.action(milk);
                System.out.print("Write how many grams of coffee beans you want to add:\n");
                String coffee = scanner.next();
                coffeeMachine.action(coffee);
                System.out.print("Write how many disposable coffee cups you want to add:\n");
                String cups = scanner.next();
                coffeeMachine.action(cups);
                System.out.print("Write how much money you want to add:\n");
                String grn = scanner.next();
                coffeeMachine.action(grn);
            } else if (action.equals("exit")) {
                break;
            }
        }

        System.out.println("Have a nice day!");
    }

    public void action(String command) {
        if (command.equals("buy")) {
            this.status = "make";
        } else if (command.equals("fill")) {
            this.status = "fill";
            this.counter = 0;
        } else if (command.equals("take")) {
            System.out.println("I gave you " + this.grn);
            this.grn = 0;
        } else if (command.equals("remaining")) {
            System.out.println("The coffee machine has:");
            System.out.println(this.water + " of water");
            System.out.println(this.milk + " of milk");
            System.out.println(this.coffee + " of coffee");
            System.out.println(this.cups + " of disposable cups");
            System.out.println(this.grn + " of money");
        } else if (this.status.equals("make")) {
            int type;
            try {
                type = Integer.parseInt(command);
            } catch (NumberFormatException e) {
                type = 4;
            }

            if (type == 4) {
                this.status = "wait";
                return;
            }

            if (type == 1) {
                makeCoffee(4, 250, 16, 0); // espresso
            } else if (type == 2) {
                makeCoffee(7, 350, 20, 75); // latte
            } else if (type == 3) {
                makeCoffee(6, 200, 10, 100); // cappuccino
            }

            this.status = "wait";
        } else if (this.status.equals("fill")) {
            int v = Integer.parseInt(command);

            if (this.counter == 0) {
                this.water += v;
            } else if (this.counter == 1) {
                this.coffee += v;
            } else if (this.counter == 2) {
                this.milk += v;
            } else if (this.counter == 3) {
                this.cups += v;
            } else if (this.counter == 4) {
                this.grn += v;
                this.status = "wait";
                this.counter = -1;
            }

            this.counter++;
        } else {
            this.status = "wait";
        }
    }

    public void makeCoffee(int takeGrn, int nWater, int nCoffee, int nMilk) {
        if (this.water < nWater) {
            System.out.println("Sorry, not enough water!");
        } else if (this.coffee < nCoffee) {
            System.out.println("Sorry, not enough coffee!");
        } else if (this.milk < nMilk) {
            System.out.println("Sorry, not enough milk!");
        } else if (this.cups < 1) {
            System.out.println("Sorry, not enough cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            this.water -= nWater;
            this.coffee -= nCoffee;
            this.milk -= nMilk;
            this.cups--;
            this.grn += takeGrn;
        }
    }
}
