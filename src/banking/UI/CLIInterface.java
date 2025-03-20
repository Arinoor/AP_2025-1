package banking.UI;

import java.util.Scanner;

public class CLIInterface {

        private static CLIInterface instance;
        private final Scanner scanner;

        private CLIInterface(){
                scanner =  new Scanner(System.in);
                displayWelcomeMessage();
                displayMenu();
        }

        public static CLIInterface getInstance() {
                if(instance == null)
                        instance = new CLIInterface();
                return instance;
        }

        public void displayWelcomeMessage() {
                System.out.println("Welcome to the Arian Banking System!");
        }

        public void displayMenu() {
                System.out.println("\nPlease enter a command:");
                System.out.println("  signup <username> <password>");
                System.out.println("  login <username> <password>");
                System.out.println("  balance");
                System.out.println("  history");
                System.out.println("  deposit <amount>");
                System.out.println("  withdraw <amount>");
                System.out.println("  transfer <destinationUsername> <amount>");
                System.out.println("  undo <transactionID>");
                System.out.println("  logout");
        }

        public String readCommand() {
                System.out.print("> ");
                return scanner.nextLine().trim();
        }

        public void displayError(String error) {
                System.err.println("Error : " + error);
        }
}
