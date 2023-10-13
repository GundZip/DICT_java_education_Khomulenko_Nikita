import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Hangman{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("HANGMAN");
        System.out.println("The game will be available soon.");

        String[] words = {"python", "C++", "html", "javascript"};
        boolean play = true;

        while (play) {
            System.out.print("Type \"play\" to play the game, \"exit\" to quit:\n>");
            String menu = scanner.nextLine();

            while (!menu.equals("play") && !menu.equals("exit")) {
                System.out.print("Type \"play\" to play the game, \"exit\" to quit:\n>");
                menu = scanner.nextLine();
            }

            if (menu.equals("play")) {
                String word = words[random.nextInt(words.length)];
                Set<Character> rememberLetters = new HashSet<>();
                Set<Character> inputLetters = new HashSet<>();
                Set<Character> allLetters = new HashSet<>();
                for (char c : word.toCharArray()) {
                    allLetters.add(c);
                }

                StringBuilder wordDisplay = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    wordDisplay.append("_");
                }

                System.out.println(wordDisplay);
                int life = 8;

                while (life > 0) {
                    System.out.print("Input a letter: ");
                    String input = scanner.nextLine();

                    if (input.length() == 1 && Character.isLowerCase(input.charAt(0))) {
                        char letter = input.charAt(0);

                        if (rememberLetters.contains(letter) || inputLetters.contains(letter)) {
                            System.out.println("You've already guessed this letter");
                        } else if (word.contains(String.valueOf(letter))) {
                            rememberLetters.add(letter);
                            inputLetters.add(letter);
                        } else {
                            System.out.println("That letter doesn't appear in the word");
                            life--;
                        }

                        wordDisplay.setLength(0);
                        for (int i = 0; i < word.length(); i++) {
                            char c = word.charAt(i);
                            if (rememberLetters.contains(c)) {
                                wordDisplay.append(c);
                            } else {
                                wordDisplay.append("_");
                            }
                        }

                        System.out.println(wordDisplay);

                        if (!wordDisplay.toString().contains("_")) {
                            System.out.println("You guessed the word\nYou survived!");
                            break;
                        }
                    } else {
                        System.out.println("Please enter a lowercase English letter");
                    }
                }

                if (life == 0) {
                    System.out.println("You lost! The word was: " + word);
                }
            } else {
                play = false;
            }
        }

        System.out.println("Thanks for playing\nBye");
    }
}
