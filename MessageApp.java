import java.util.Scanner;

/**
 * MessageApp class - Main application with menu-driven interface
 * Allows users to input messages and manage them
 */
public class MessageApp {
    private MessageManager manager;
    private Scanner scanner;

    // Constructor
    public MessageApp() {
        this.manager = new MessageManager();
        this.scanner = new Scanner(System.in);
    }

    // Main menu
    public void displayMainMenu() {
        System.out.println("\n========================================");
        System.out.println("     MESSAGE MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add a new message");
        System.out.println("2. View Stored Messages (Submenu)");
        System.out.println("3. View all Sent Messages");
        System.out.println("4. View all Disregarded Messages");
        System.out.println("5. Exit");
        System.out.println("========================================");
    }

    // Submenu for Stored Messages
    public void displayStoredMessagesSubmenu() {
        System.out.println("\n========================================");
        System.out.println("      STORED MESSAGES SUBMENU");
        System.out.println("========================================");
        System.out.println("a. Display all stored messages");
        System.out.println("b. Display sender and recipient of stored messages");
        System.out.println("c. Display the longest stored message");
        System.out.println("d. Search for a message by ID");
        System.out.println("e. Search messages by recipient");
        System.out.println("f. Delete a message using message hash");
        System.out.println("g. Display report of all sent messages");
        System.out.println("h. Return to Main Menu");
        System.out.println("========================================");
    }

    // Add a new message
    public void addNewMessage() {
        System.out.println("\n===== ADD NEW MESSAGE =====");
        System.out.print("Enter sender (phone number): ");
        String sender = scanner.nextLine().trim();

        System.out.print("Enter recipient (phone number): ");
        String recipient = scanner.nextLine().trim();

        System.out.print("Enter message content: ");
        String messageContent = scanner.nextLine().trim();

        System.out.println("Select message flag:");
        System.out.println("1. Sent");
        System.out.println("2. Disregarded");
        System.out.println("3. Stored");
        System.out.print("Enter choice (1-3): ");
        String flagChoice = scanner.nextLine().trim();

        String flag = "";
        switch (flagChoice) {
            case "1":
                flag = "Sent";
                break;
            case "2":
                flag = "Disregarded";
                break;
            case "3":
                flag = "Stored";
                break;
            default:
                System.out.println("Invalid choice. Message not added.");
                return;
        }

        Message message = new Message(sender, recipient, messageContent, flag);
        if (manager.addMessage(message)) {
            System.out.println("\n✓ Message added successfully!");
            System.out.println("Message ID: " + message.getMessageID());
            System.out.println("Message Hash: " + message.getMessageHash());
        } else {
            System.out.println("\n✗ Error: Could not add message. Maximum capacity reached.");
        }
    }

    // Run the application
    public void run() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║  Welcome to Message Management System ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean running = true;

        while (running) {
            displayMainMenu();
            System.out.print("Enter your choice (1-5): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addNewMessage();
                    break;
                case "2":
                    handleStoredMessagesSubmenu();
                    break;
                case "3":
                    manager.displaySentMessages();
                    break;
                case "4":
                    manager.displayDisregardedMessages();
                    break;
                case "5":
                    System.out.println("\n╔══════════════════════════════════════╗");
                    System.out.println("║        Thank you for using our app    ║");
                    System.out.println("║              Goodbye!                  ║");
                    System.out.println("╚══════════════════════════════════════╝\n");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // Handle the Stored Messages submenu
    private void handleStoredMessagesSubmenu() {
        boolean inSubmenu = true;

        while (inSubmenu) {
            displayStoredMessagesSubmenu();
            System.out.print("Enter your choice (a-h): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "a":
                    manager.displayStoredMessages();
                    break;
                case "b":
                    manager.displaySenderAndRecipientOfStoredMessages();
                    break;
                case "c":
                    manager.displayLongestStoredMessage();
                    break;
                case "d":
                    System.out.print("Enter message ID to search: ");
                    String messageID = scanner.nextLine().trim();
                    manager.searchMessageByID(messageID);
                    break;
                case "e":
                    System.out.print("Enter recipient to search: ");
                    String recipient = scanner.nextLine().trim();
                    manager.searchMessagesByRecipient(recipient);
                    break;
                case "f":
                    System.out.print("Enter message hash to delete: ");
                    String messageHash = scanner.nextLine().trim();
                    manager.deleteMessageByHash(messageHash);
                    break;
                case "g":
                    manager.displayReport();
                    break;
                case "h":
                    System.out.println("Returning to Main Menu...");
                    inSubmenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        MessageApp app = new MessageApp();
        app.run();
    }
}
