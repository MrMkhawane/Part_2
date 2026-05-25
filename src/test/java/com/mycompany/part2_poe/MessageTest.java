/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.part2_poe;

import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class MessageTest {
    
    public MessageTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }



    @Test
    public void testCheckmessage() {
        System.out.println("checkmessage");
        Message message = new Message();
        String messages = "Despite the heavy rain and thunderstorm warning, and the determined hikers continued their journey up the misty mountain trail, relying on their maps, compasses, and teamwork to guide them safely through the dense forest and unpredictable terrain ahead";
        boolean expResult = false;
        boolean result = message.checkmessage(messages);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        Message message = new Message();
        String phone_number = "+27896650953";
        boolean expResult = true;
        boolean result = message.checkCellPhoneNumber(phone_number);
        assertEquals(expResult, result);

    }

    /**
     * Test of createMessageHash method, of class Message.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        Message message = new Message();
        int count = 1;
        String messages = "Hi , Thanks for tonight";
        long messageID = 1234567890L;
        String expResult = "12:1:HITONIGHT";
        String result = message.createMessageHash(count, messages, messageID);
        assertEquals(expResult, result);
    }

    /**
     * Test of SentMessage method, of class Message.
     */
    @Test
    public void testSentMessage() {
        System.out.println("SentMessage");
        Message message = new Message();
        Random random = new Random();
        int count = 1;
        String messages = "Hello there, how are you?";
        String number = "+2785350865";
        String expResult = "Message successfully stored.";
        String result = message.SentMessage(random, count, messages, number);
        assertEquals(expResult, result);
    }

    /**
     * Test of returnTotalMessages method, of class Message.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        Message message = new Message();
        int messages_sent = 3;
        int expResult = 3;
        int result = message.returnTotalMessages(messages_sent);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkMessageID method, of class Message.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        Message message = new Message();
        long messageID = 1234567890L;
        boolean expResult = true;
        boolean result = message.checkMessageID(messageID);
        assertEquals(expResult, result);
    }

    /**
     * Test of storeMessages method, of class Message.
     */
    @Test
    public void testStoreMessages() {
        System.out.println("storeMessages");
        Random random = new Random();
        int count = 1;
        String messages = "Hello";
        String number = "+27121231234";
        Message message = new Message();
        message.storeMessages(random, count, messages, number);
    }

    /**
     * Test of createMessageID method, of class Message.
     */
    @Test
    public void testCreateMessageID() {
        Random random1 = new Random(12345);
        Message message = new Message();
        long expResult = message.createMessageID(random1);

        Random random2 = new Random(12345);
        long result = message.createMessageID(random2);
        assertEquals(expResult, result);
    }

    /**
     * Test of printMessage method, of class Message.
     */
    @Test
    public void testPrintMessage() {
        System.out.println("Testing printMessage method");

        int count = 2;
        String messages = "Hello there, how are you doing ?";
        String number = "+27121231234";

        Message message = new Message();
        Random random1 = new Random(12345);

        long MessageID = message.createMessageID(random1);

        String MessageHash = message.createMessageHash(count, messages, MessageID);

        String expResult = "Message ID: " + MessageID + "\n"
                + "Message Hash: " + MessageHash + "\n"
                + "Messages sent: " + count + "\n"
                + "Recipient: " + number + "\n"
                + "Message: " + messages;

        Random random2 = new Random(12345);
        String result = message.printMessage(random2, count, messages, number);
        assertEquals(expResult, result);
    }

    
}
