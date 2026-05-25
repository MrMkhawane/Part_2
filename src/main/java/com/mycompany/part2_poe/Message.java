/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part2_poe;

import javax.swing.*;
import java.util.Random;
import java.io.*;
import java.util.Date;

/**
 *
 * @author RC_Student_lab
 */
public class Message extends Login {

    private String number = null;
    private String messages = null;
    private int messages_sent;
    private int sum = 0;
    private String[] send = new String[100];
    private String[] store = new String[100];
    private String[] disregard = new String[100];

    //Number
    public String getnumber() {
        return number;
    }

    public void setnumber(String number) {
        this.number = number;
    }

    //message
    public String getmessages() {
        return messages;
    }

    public void setmessages(String messages) {
        this.messages = messages;
    }

    //Messages sent
    public int getmessages_sent() {
        return messages_sent;
    }

    public void setmessages_sent(int messages_sent) {
        this.messages_sent = messages_sent;
    }

    //send array
    public String[] getsend() {
        return send;
    }

    public void setsend(String[] send) {
        this.send = send;
    }

    /**
     * ********METHODS**************************************************************
     */
    public int returnTotalMessages(int messages_sent) {
        this.sum += messages_sent;
        return this.sum;
    }

    //Generates a MessageID
    public long createMessageID(Random random) {
        return 1000000000L + (Math.abs(random.nextLong()) % 9000000000L);
    }

    //checks if the messageID meets the conditions(it has the length of 10)
    public boolean checkMessageID(long messageID) {

        String mess_ID = Long.toString(messageID);
        if (mess_ID.length() == 10) {
            return true;
        } else {
            return false;
        }
    }

    //creates a message hash
    public String createMessageHash(int count, String messages, long messageID) {

        String massages_sent = Integer.toString(count);

        int first_space_index = messages.indexOf(" ");
        String first_word;

        // If there is a space in the string, get the substring up to the first space
        if (first_space_index != -1) {
            first_word = messages.substring(0, first_space_index);
        } // If there is no space, the whole string is the first word
        else {
            first_word = messages;
        }

        int last_space_index = messages.lastIndexOf(" ");
        String last_word = messages.substring(last_space_index + 1);

        String ID = Long.toString(messageID);//Convented the messageID from Long to a String
        String message_hash = ID.substring(0, 2) + ":" + massages_sent + ":" + first_word.toUpperCase() + last_word.toUpperCase();

        return message_hash;
    }

    //Checks if the messages are less than 250 characters and greater than 0.
    public boolean checkmessage(String messages) {

        if (messages.length() > 250) {
            JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + messages.length() + ", please reduce size.");
            return false;
        } else if (messages.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Message, it can not be empty.");
            return false;
        } else {
            //System.out.println("Message ready to send.");
            JOptionPane.showMessageDialog(null, "Message ready to send.");
            return true;
        }
    }

    //Asks the user if they want to send/store/delete the message
    public String SentMessage(Random random, int count, String messages, String number) {

        String[] message_options = {"Send", "Store to send later", "Disregard"};
        int select = JOptionPane.showOptionDialog(null, "Choose an option:", "Select Option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, message_options, message_options[0]);

        if (select == 0) {
            JOptionPane.showMessageDialog(null, printMessage(random, count, messages, number));
            for (int i = 0; i < store.length; i++) {
                if (send[i] == null) {
                    send[i] = messages;
                    break;
                }
            }
            return "Message successfully sent.";
        } else if (select == 1) {
            for (int i = 0; i < store.length; i++) {
                if (store[i] == null) {
                    store[i] = messages;
                    break;
                }
            }
            return "Message successfully stored.";
        } else if (select == 2) {
            for (int i = 0; i < store.length; i++) {
                if (disregard[i] == null) {
                    disregard[i] = messages;
                    break;
                }
            }
            return "Press 0 to delete message.";
        }

        return "No valid option selected";
    }

    public String printMessage(Random random, int count, String messages, String number) {

        long messageID = createMessageID(random);
        checkMessageID(messageID);

        String output
                = "Message ID: " + messageID + "\n"
                + "Message Hash: " + createMessageHash(count, messages, messageID) + "\n"
                + "Messages sent: " + count + "\n"
                + "Recipient: " + number + "\n"
                + "Message: " + messages;

        return output;
    }

    public void storeMessages(Random random, int count, String messages, String number) {
        try {
            File my_file = new File("STOREMESSAGES.JSON");
            if (!my_file.exists()) {
                if (my_file.createNewFile()) {
                    System.out.println("File created: " + my_file.getName());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred creating the file: ");
            e.printStackTrace();
            return;
        }

        
        try (FileWriter my_writer = new FileWriter("STOREMESSAGES.JSON", true)) {
            Date date = new Date();
            long messageID = createMessageID(random);
            checkMessageID(messageID);

            if (count == 1) {
                my_writer.write(
                        "\n/*******************************************************/"
                        + "\n/************************NEW CHAT***********************/"
                        + "\n/*******************************************************/\n"
                        + printMessage(random, count, messages, number) + "\n Date and Time : " + date
                        + "\n/*******************************************************/\n");
            } else {
                my_writer.write(
                        printMessage(random, count, messages, number) + "\nDate and Time : " + date
                        + "\n/*******************************************************/\n");
            }

            JOptionPane.showMessageDialog(null, "SUCCESSFULLY STORED MESSAGES IN THE FILE.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred writing to the file: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
