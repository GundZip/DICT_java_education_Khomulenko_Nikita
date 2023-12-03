package RockPaperScissors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    static String[] options = {"rock", "gun", "lightning", "devil", "dragon", "water", "air", "paper",
            "sponge", "wolf", "tree", "human", "snake", "scissors", "fire"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome \n" +
                "Power by Stell!");

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.println("Hello, " + userName);

        int userScore = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader("rating.txt"))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                if (name.equals(userName)) {
                    userScore = score;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Okay, let's start \n" +
                "Select one choice: rock, gun, lightning, devil, dragon, water, air, paper," +
                "sponge, wolf, tree, human, snake, scissors, fire \n and !help");

        while (true) {
            String userChoice = scanner.nextLine().toLowerCase();

            if (userChoice.equals("!exit")) {
                System.out.println("Bye!");
                break;
            }

            if (userChoice.equals("!help")) {
                System.out.println("Available commands: ");
                System.out.println("!exit - quit the game");
                System.out.println("!rating - show your rating");
                continue;
            }

            if (userChoice.equals("!rating")) {
                System.out.println("Your rating: " + userScore);
                continue;
            }

            int computerIndex = random.nextInt(options.length);
            String computerChoice = options[computerIndex];

            if (userChoice.equals(computerChoice)) {
                System.out.println("There is a draw (" + computerChoice + ")");
                userScore += 50;
            } else if ((userChoice.equals("rock") && computerChoice.matches("scissors|snake|human|tree|wolf|sponge|paper")) ||
                    (userChoice.equals("gun") && computerChoice.matches("lightning|devil|dragon|water|air|paper|sponge")) ||
                    (userChoice.equals("lightning") && computerChoice.matches("devil|dragon|water|air|paper|sponge|wolf")) ||
                    (userChoice.equals("devil") && computerChoice.matches("dragon|water|air|paper|sponge|wolf|tree|human|snake|scissors|fire|rock|gun")) ||
                    (userChoice.equals("dragon") && computerChoice.matches("water|air|paper|sponge|wolf|tree|human|snake|scissors|fire|rock|gun|lightning")) ||
                    (userChoice.equals("water") && computerChoice.matches("air|paper|sponge|wolf|tree|human|snake|scissors|fire|rock|gun|lightning|devil")) ||
                    (userChoice.equals("air") && computerChoice.matches("paper|sponge|wolf|tree|human|snake|scissors|fire|rock|gun|lightning|devil|dragon")) ||
                    (userChoice.equals("paper") && computerChoice.matches("sponge|wolf|tree|human|snake|scissors|fire|rock|gun|lightning|devil|dragon|water")) ||
                    (userChoice.equals("sponge") && computerChoice.matches("wolf|tree|human|snake|scissors|fire|rock|gun|lightning|devil|dragon|water|air")) ||
                    (userChoice.equals("wolf") && computerChoice.matches("tree|human|snake|scissors|fire|rock|gun|lightning|devil|dragon|water|air|paper")) ||
                    (userChoice.equals("tree") && computerChoice.matches("human|snake|scissors|fire|rock|gun|lightning|devil|dragon|water|air|paper|sponge")) ||
                    (userChoice.equals("human") && computerChoice.matches("snake|scissors|fire|rock|gun|lightning|devil|dragon|water|air|paper|sponge|wolf")) ||
                    (userChoice.equals("snake") && computerChoice.matches("scissors|fire|rock|gun|lightning|devil|dragon|water|air|paper|sponge|wolf|tree")) ||
                    (userChoice.equals("scissors") && computerChoice.matches("fire|rock|gun|lightning|devil|dragon|water|air|paper|sponge|wolf|tree|human")) ||
                    (userChoice.equals("fire") && computerChoice.matches("rock|gun|lightning|devil|dragon|water|air|paper|sponge|wolf|tree|human|snake"))) {
                System.out.println("Well done. The computer chose " + computerChoice + " and failed");
                userScore += 100;
            } else {
                System.out.println("Sorry, but the computer chose " + computerChoice);
                System.out.println("Your rating: " + userScore);
            }
        }

        System.out.println("Have a nice day!");

        try (FileWriter fileWriter = new FileWriter("rating.txt", true)) {
            fileWriter.write(userName + " " + userScore + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
