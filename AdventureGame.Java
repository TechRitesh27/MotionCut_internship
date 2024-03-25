import java.util.Scanner;

public class AdventureGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("------------Welcome to the Text Adventure Game!-----------");
        System.out.println("");
        System.out.println("You wake up in a mysterious cave...");

        // Initial decision point
        System.out.println("What do you do?");
        System.out.println("1. Explore deeper into the cave.");
        System.out.println("2. Try to find a way out of the cave.");

        int choice = getUserChoice(scanner, 2);
        System.out.println("");

        if (choice == 1) {
            exploreCave(scanner);
        } else if (choice == 2) {
            findExit(scanner);
        }

        scanner.close();
    }

    // Method to handle exploring the cave
    public static void exploreCave(Scanner scanner) {
        System.out.println("You venture deeper into the cave and encounter two tunnels !!!.");
        System.out.println("Which tunnel do you choose?");
        System.out.println("1. Dark and narrow tunnel.");
        System.out.println("2. Well-lit tunnel.");

        int choice = getUserChoice(scanner, 2);
        System.out.println("");

        if (choice == 1) {
            darkTunnel(scanner);
        } else if (choice == 2) {
            wellLitTunnel(scanner);
        }
    }

    // Method to handle choosing the dark tunnel
    public static void darkTunnel(Scanner scanner) {
        System.out.println("You enter the dark tunnel and hear growling sounds approaching.");
        System.out.println("What do you do?");
        System.out.println("1. Hide in the shadows.");
        System.out.println("2. Run back to the main cave.");

        int choice = getUserChoice(scanner, 2);
        System.out.println("");

        if (choice == 1) {
            System.out.println("You hide in the shadows and manage to evade the danger. You continue exploring...");
            System.out.println("");
        } else if (choice == 2) {
            System.out.println("You run back to the main cave. The adventure continues...");
            System.out.println("");
        }
    }

    // Method to handle choosing the well-lit tunnel
    public static void wellLitTunnel(Scanner scanner) {
        System.out.println("You enter the well-lit tunnel and find a treasure chest.");
        System.out.println("What do you do?");
        System.out.println("1. Open the treasure chest.");
        System.out.println("2. Leave the treasure chest and continue exploring.");

        int choice = getUserChoice(scanner, 2);
        System.out.println("");

        if (choice == 1) {
            System.out.println("You open the treasure chest and find valuable artifacts. You win!");
        } else if (choice == 2) {
            System.out.println("You leave the treasure chest and continue exploring. The adventure continues...");
            // Additional scenarios and decisions can be added here
        }
    }

    // Method to handle finding the exit from the cave
    public static void findExit(Scanner scanner) {
        System.out.println("You search for an exit and find two paths.");
        System.out.println("Which path do you choose?");
        System.out.println("1. Upwards, towards the light.");
        System.out.println("2. Downwards, into the darkness.");

        int choice = getUserChoice(scanner, 2);
        System.out.println("");

        if (choice == 1) {
            towardsLight(scanner);
        } else if (choice == 2) {
            intoDarkness(scanner);
        }
    }

    // Method to handle choosing the path towards the light
    public static void towardsLight(Scanner scanner) {
        System.out.println("You ascend towards the light and emerge from the cave.");
        System.out.println("Congratulations! You have found your way out and completed the adventure!");
    }

    // Method to handle choosing the path into darkness
    public static void intoDarkness(Scanner scanner) {
        System.out.println("You descend into the darkness and encounter a dead end.");
        System.out.println("Unfortunately, you couldn't find a way out. Game Over!");
    }

    // Method to get user choice with error handling
    public static int getUserChoice(Scanner scanner, int maxChoice) {
        int choice = 0;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= maxChoice) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + maxChoice + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }

        return choice;
    }
}