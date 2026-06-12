import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for MessageManager class
 * Tests all functionality with the provided test data
 */
public class MessageManagerTest {
    private MessageManager manager;

    @Before
    public void setUp() {
        manager = new MessageManager();
    }

    /**
     * Test: Sent Messages array is correctly populated
     * Test Data: Developer entry for Test data for message 1-4
     */
    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        // Test Data Message 1
        Message msg1 = new Message("27834557895", "Did you get the cake?", "27834557895", "Sent");
        // Test Data Message 4
        Message msg4 = new Message("0838884567", "It is dinner time !", "0838884567", "Sent");

        manager.addMessage(msg1);
        manager.addMessage(msg4);

        assertEquals("Sent messages count should be 2", 2, manager.getSentCount());
        assertEquals("First message should be from 27834557895", "27834557895", manager.getSentMessages()[0].getSender());
        assertEquals("Second message should be from 0838884567", "0838884567", manager.getSentMessages()[1].getSender());
    }

    /**
     * Test: Display the longest Message
     * Test Data: message 1-4
     * System returns: "Where are you? You are late! I have asked you to be on time."
     */
    @Test
    public void testDisplayLongestStoredMessage() {
        Message msg1 = new Message("27834557895", "Did you get the cake?", "27834557895", "Stored");
        Message msg2 = new Message("27838884567", "Where are you? You are late! I have asked you to be on time.", "27838884567", "Stored");
        Message msg3 = new Message("27834448567", "Yohoooo, I am at your gate.", "27834448567", "Stored");

        manager.addMessage(msg1);
        manager.addMessage(msg2);
        manager.addMessage(msg3);

        // The longest message should be msg2
        int longest = 0;
        for (int i = 0; i < manager.getStoredCount(); i++) {
            if (manager.getStoredMessages()[i].getMessageContent().length() >
                manager.getStoredMessages()[longest].getMessageContent().length()) {
                longest = i;
            }
        }

        assertEquals("Longest message should have content from msg2",
                "Where are you? You are late! I have asked you to be on time.",
                manager.getStoredMessages()[longest].getMessageContent());
    }

    /**
     * Test: Search for messageID
     * Test Data: message 4
     * System returns: "It is dinner time!"
     */
    @Test
    public void testSearchForMessageID() {
        Message msg4 = new Message("0838884567", "It is dinner time !", "0838884567", "Stored");
        manager.addMessage(msg4);

        String messageID = msg4.getMessageID();
        Message found = null;

        for (int i = 0; i < manager.getStoredCount(); i++) {
            if (manager.getStoredMessages()[i].getMessageID().equals(messageID)) {
                found = manager.getStoredMessages()[i];
                break;
            }
        }

        assertNotNull("Message should be found", found);
        assertEquals("Message content should match", "It is dinner time !", found.getMessageContent());
    }

    /**
     * Test: Search all the messages sent for a particular recipient
     * Test Data: +27838884567
     * System returns:
     * "Where are you? You are late! I have asked you to be on time." +
     * "Ok, I am leaving without you."
     */
    @Test
    public void testSearchMessagesByParticularRecipient() {
        Message msg2 = new Message("27838884567", "Where are you? You are late! I have asked you to be on time.", "27838884567", "Stored");
        Message msg5 = new Message("27838884567", "Ok, I am leaving without you.", "27838884567", "Stored");
        Message msg3 = new Message("27834448567", "Yohoooo, I am at your gate.", "27834448567", "Stored");

        manager.addMessage(msg2);
        manager.addMessage(msg5);
        manager.addMessage(msg3);

        String recipient = "27838884567";
        int count = 0;
        for (int i = 0; i < manager.getStoredCount(); i++) {
            if (manager.getStoredMessages()[i].getRecipient().equals(recipient) ||
                manager.getStoredMessages()[i].getSender().equals(recipient)) {
                count++;
            }
        }

        assertEquals("Should find 2 messages for recipient 27838884567", 2, count);
    }

    /**
     * Test: Delete a message using a message hash
     * Test Data: Test Message 2
     * System returns: Message "Where are you? You are late! I have asked you to be on time." successfully deleted.
     */
    @Test
    public void testDeleteMessageUsingMessageHash() {
        Message msg2 = new Message("27838884567", "Where are you? You are late! I have asked you to be on time.", "27838884567", "Stored");
        manager.addMessage(msg2);

        String hashToDelete = msg2.getMessageHash();
        int initialCount = manager.getStoredCount();

        boolean deleted = manager.deleteMessageByHash(hashToDelete);

        assertTrue("Message should be deleted", deleted);
        assertEquals("Stored message count should decrease by 1", initialCount - 1, manager.getStoredCount());
    }

    /**
     * Test: Display Report
     * System returns: a report that shows all the sent messages, including the:
     * - Message Hash
     * - Recipient
     * - Message
     */
    @Test
    public void testDisplayReport() {
        Message msg1 = new Message("27834557895", "Did you get the cake?", "27834557895", "Sent");
        Message msg4 = new Message("0838884567", "It is dinner time !", "0838884567", "Sent");

        manager.addMessage(msg1);
        manager.addMessage(msg4);

        assertEquals("Should have 2 sent messages", 2, manager.getSentCount());
        assertNotNull("Message hash should exist", manager.getSentMessages()[0].getMessageHash());
        assertEquals("Recipient should match", "27834557895", manager.getSentMessages()[0].getRecipient());
    }

    /**
     * Test: Add multiple messages to different arrays
     */
    @Test
    public void testAddMultipleMessagesToDifferentArrays() {
        Message sentMsg = new Message("1234567890", "Hello", "1234567890", "Sent");
        Message disregardedMsg = new Message("0987654321", "Ignored", "0987654321", "Disregarded");
        Message storedMsg = new Message("5555555555", "Stored msg", "5555555555", "Stored");

        manager.addMessage(sentMsg);
        manager.addMessage(disregardedMsg);
        manager.addMessage(storedMsg);

        assertEquals("Should have 1 sent message", 1, manager.getSentCount());
        assertEquals("Should have 1 disregarded message", 1, manager.getDisregardedCount());
        assertEquals("Should have 1 stored message", 1, manager.getStoredCount());
    }

    /**
     * Test: Verify message properties are correctly set
     */
    @Test
    public void testMessagePropertiesCorrectlySet() {
        Message msg = new Message("1234567890", "9876543210", "Test message", "Sent");

        assertEquals("Sender should match", "1234567890", msg.getSender());
        assertEquals("Recipient should match", "9876543210", msg.getRecipient());
        assertEquals("Message content should match", "Test message", msg.getMessageContent());
        assertEquals("Flag should be Sent", "Sent", msg.getFlag());
        assertNotNull("Message hash should not be null", msg.getMessageHash());
        assertNotNull("Message ID should not be null", msg.getMessageID());
    }
}
