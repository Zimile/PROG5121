/**
 * MessageManager class handles all message storage and operations
 * Manages arrays for: Sent Messages, Disregarded Messages, Stored Messages, Message Hashes, and Message IDs
 */
public class MessageManager {
    private Message[] sentMessages;
    private Message[] disregardedMessages;
    private Message[] storedMessages;
    private int sentCount = 0;
    private int disregardedCount = 0;
    private int storedCount = 0;
    private final int MAX_MESSAGES = 100;  // Initial capacity

    // Constructor
    public MessageManager() {
        this.sentMessages = new Message[MAX_MESSAGES];
        this.disregardedMessages = new Message[MAX_MESSAGES];
        this.storedMessages = new Message[MAX_MESSAGES];
    }

    // Add a message to the appropriate array based on flag
    public boolean addMessage(Message message) {
        String flag = message.getFlag();

        if (flag.equalsIgnoreCase("Sent")) {
            if (sentCount < MAX_MESSAGES) {
                sentMessages[sentCount] = message;
                sentCount++;
                return true;
            }
        } else if (flag.equalsIgnoreCase("Disregarded")) {
            if (disregardedCount < MAX_MESSAGES) {
                disregardedMessages[disregardedCount] = message;
                disregardedCount++;
                return true;
            }
        } else if (flag.equalsIgnoreCase("Stored")) {
            if (storedCount < MAX_MESSAGES) {
                storedMessages[storedCount] = message;
                storedCount++;
                return true;
            }
        }
        return false;
    }

    // Display all sent messages
    public void displaySentMessages() {
        System.out.println("\n===== SENT MESSAGES =====");
        if (sentCount == 0) {
            System.out.println("No sent messages.");
            return;
        }
        for (int i = 0; i < sentCount; i++) {
            System.out.println("Message " + (i + 1) + ":");
            System.out.println(sentMessages[i]);
        }
    }

    // Display all disregarded messages
    public void displayDisregardedMessages() {
        System.out.println("\n===== DISREGARDED MESSAGES =====");
        if (disregardedCount == 0) {
            System.out.println("No disregarded messages.");
            return;
        }
        for (int i = 0; i < disregardedCount; i++) {
            System.out.println("Message " + (i + 1) + ":");
            System.out.println(disregardedMessages[i]);
        }
    }

    // Display all stored messages
    public void displayStoredMessages() {
        System.out.println("\n===== STORED MESSAGES =====");
        if (storedCount == 0) {
            System.out.println("No stored messages.");
            return;
        }
        for (int i = 0; i < storedCount; i++) {
            System.out.println("Message " + (i + 1) + ":");
            System.out.println(storedMessages[i]);
        }
    }

    // Display sender and recipient of all stored messages
    public void displaySenderAndRecipientOfStoredMessages() {
        System.out.println("\n===== SENDER AND RECIPIENT OF STORED MESSAGES =====");
        if (storedCount == 0) {
            System.out.println("No stored messages.");
            return;
        }
        for (int i = 0; i < storedCount; i++) {
            System.out.println("Message " + (i + 1) + ":");
            System.out.println("Sender: " + storedMessages[i].getSender());
            System.out.println("Recipient: " + storedMessages[i].getRecipient());
            System.out.println();
        }
    }

    // Display the longest stored message
    public void displayLongestStoredMessage() {
        System.out.println("\n===== LONGEST STORED MESSAGE =====");
        if (storedCount == 0) {
            System.out.println("No stored messages.");
            return;
        }

        int longestIndex = 0;
        int maxLength = storedMessages[0].getMessageContent().length();

        for (int i = 1; i < storedCount; i++) {
            int currentLength = storedMessages[i].getMessageContent().length();
            if (currentLength > maxLength) {
                maxLength = currentLength;
                longestIndex = i;
            }
        }

        System.out.println(storedMessages[longestIndex]);
    }

    // Search for a message by ID in stored messages
    public void searchMessageByID(String messageID) {
        System.out.println("\n===== SEARCH MESSAGE BY ID =====");
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].getMessageID().equals(messageID)) {
                System.out.println("Message found:");
                System.out.println(storedMessages[i]);
                return;
            }
        }
        System.out.println("Message with ID " + messageID + " not found.");
    }

    // Search all messages sent to or from a particular recipient
    public void searchMessagesByRecipient(String recipient) {
        System.out.println("\n===== SEARCH MESSAGES BY RECIPIENT: " + recipient + " =====");
        boolean found = false;

        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].getRecipient().equalsIgnoreCase(recipient) ||
                storedMessages[i].getSender().equalsIgnoreCase(recipient)) {
                System.out.println("Message " + (i + 1) + ":");
                System.out.println(storedMessages[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found for recipient: " + recipient);
        }
    }

    // Delete a message using message hash
    public boolean deleteMessageByHash(String messageHash) {
        System.out.println("\n===== DELETE MESSAGE BY HASH =====");
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].getMessageHash().equals(messageHash)) {
                System.out.println("Message deleted:");
                System.out.println(storedMessages[i]);
                // Shift elements
                for (int j = i; j < storedCount - 1; j++) {
                    storedMessages[j] = storedMessages[j + 1];
                }
                storedMessages[storedCount - 1] = null;
                storedCount--;
                return true;
            }
        }
        System.out.println("Message with hash " + messageHash + " not found.");
        return false;
    }

    // Display a report of all sent messages with details
    public void displayReport() {
        System.out.println("\n===== COMPLETE REPORT OF SENT MESSAGES =====");
        if (sentCount == 0) {
            System.out.println("No sent messages to report.");
            return;
        }
        System.out.println("Total Sent Messages: " + sentCount);
        System.out.println();
        for (int i = 0; i < sentCount; i++) {
            System.out.println("Message " + (i + 1) + ":");
            System.out.println("Message Hash: " + sentMessages[i].getMessageHash());
            System.out.println("Recipient: " + sentMessages[i].getRecipient());
            System.out.println("Message: " + sentMessages[i].getMessageContent());
            System.out.println();
        }
    }

    // Getters for array sizes
    public int getSentCount() {
        return sentCount;
    }

    public int getDisregardedCount() {
        return disregardedCount;
    }

    public int getStoredCount() {
        return storedCount;
    }

    public Message[] getSentMessages() {
        return sentMessages;
    }

    public Message[] getDisregardedMessages() {
        return disregardedMessages;
    }

    public Message[] getStoredMessages() {
        return storedMessages;
    }
}
