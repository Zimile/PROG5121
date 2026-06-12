/**
 * Message class represents a single message with sender, recipient, content, and status
 */
public class Message {
    private String sender;
    private String recipient;
    private String messageContent;
    private String flag;  // Sent, Disregarded, or Stored
    private String messageHash;
    private String messageID;

    // Constructor
    public Message(String sender, String recipient, String messageContent, String flag) {
        this.sender = sender;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.flag = flag;
        this.messageHash = generateHash();
        this.messageID = generateID();
    }

    // Generate a simple hash for the message
    private String generateHash() {
        return Integer.toHexString(messageContent.hashCode());
    }

    // Generate a unique ID for the message
    private String generateID() {
        return System.currentTimeMillis() + "_" + (int)(Math.random() * 10000);
    }

    // Getters
    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getFlag() {
        return flag;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getMessageID() {
        return messageID;
    }

    // Setters
    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Sender: " + sender + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageContent + "\n" +
               "Flag: " + flag + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Message ID: " + messageID + "\n";
    }
}
