/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.part2_poe;

import java.util.Random;
//import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class Part2_POE {

    public static void main(String[] args) {

        //Objects
        Random random = new Random();

        //Classes
        Login login = new Login();
        Message message = new Message();

        String firstName = "";
        String lastName = "";
        String userName;
        String password;
        String phoneNumber;
        String temp_username = "";
        String temp_password = "";

        //JOptionPane.showInputDialog(null, "How many messages do you wish to send: ")
        //check if Firstname has a value or not
        do {
            //Firstname
            firstName = JOptionPane.showInputDialog(null, "Enter your first name: ");
        } while (firstName == null || firstName.trim().isEmpty());
        login.setFirstname(firstName);

        //check if Lastname has a value or not
        do {
            //Lastname
            lastName = JOptionPane.showInputDialog(null, "Enter your second name: ");
        } while (lastName == null || lastName.trim().isEmpty());
        login.setLastname(lastName);

        //Registering
        boolean registered = true;

        while (registered) {

            do {
                //username
                userName = JOptionPane.showInputDialog(null, "Enter your username: ");
                login.setUsername(userName);
            } while (userName == null || login.checkUserName(login.getUsername()) == false);
            temp_username = login.getUsername();//Storing the information in a temp variable if they meet the required conditions(username)

            do {
                //password
                password = JOptionPane.showInputDialog(null, "Enter password: ");
                login.setPassword(password);
            } while (password == null || login.checkPasswordComplexity(login.getPassword()) == false);
            temp_password = login.getPassword();//Storing the information in a temp variable if they meet the required conditions(password)

            do {
                //phone number
                phoneNumber = JOptionPane.showInputDialog(null, "Enter cell phone number: ");
                login.setPhone_number(phoneNumber);
            } while (phoneNumber == null || login.checkCellPhoneNumber(login.getPhone_number()) == false);

            registered = false;
        }

        //Logging in
        boolean logging_in = true;
        while (logging_in) {
            System.out.println("********Login*******");

            do {
                //username
                userName = JOptionPane.showInputDialog(null, "Enter your username: ");
                login.setLogin_username(userName);
            } while (userName == null || login.checkUserName(login.getUsername()) == false);

            do {
                //password
                password = JOptionPane.showInputDialog(null, "Enter password: ");
                login.setLogin_password(password);
            } while (password == null || login.checkPasswordComplexity(login.getPassword()) == false);

            if (login.loginUser(temp_username, temp_password, login.getLogin_username(), login.getLogin_password())) {
                JOptionPane.showMessageDialog(null, login.returnLoginStatus(temp_username, temp_password, login.getLogin_username(), login.getLogin_password(), login.getFirstname(), login.getLastname()));
                logging_in = false;
            } else {
                JOptionPane.showMessageDialog(null, login.returnLoginStatus(temp_username, temp_password, login.getLogin_username(), login.getLogin_password(), login.getFirstname(), login.getLastname()));
            }
        }

        /**
         * *********MESSAGING************
         */
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        boolean is_messaging = true;
        while (is_messaging) {
            String[] options = {"1", "2", "3"};
            int selection = JOptionPane.showOptionDialog(null, "1)Send Messages \n2)Show recently sent messages \n3)Quit", "Select Option:",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selection == 0) {

                boolean is_input_invalid = true;

                while (is_input_invalid) {
                    //Asks the user for the number of messages do they want to send.
                    String numMessagesInput = JOptionPane.showInputDialog(null, "How many messages do you wish to send: ");

                    if (numMessagesInput == null) {
                        JOptionPane.showMessageDialog(null, "Feature coming soon");
                        continue;//It will ship to the following code, it will start the while again
                    }

                    //Tells the user to enter a number if they enter a space or nothing
                    if (numMessagesInput.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter a number: ");
                        continue;//It will ship to the following code, it will start the while again
                    }

                    message.setmessages_sent(Integer.parseInt(numMessagesInput));
                    if (message.getmessages_sent() <= 0) {
                        JOptionPane.showMessageDialog(null, "Enter a positive number(example 2): ");
                        continue;//It will ship to the following code, it will start the while again
                    }
                    message.returnTotalMessages(message.getmessages_sent());//Ensures that the number of messages sent is being captured
                    is_input_invalid = false;
                }

                //It is going to loop the number of times the user wants to send messages
                for (int count = 1; count <= message.getmessages_sent(); count++) {

                    boolean recipient_number_format = true;
                    while (recipient_number_format) {
                        //Asks the user to input the recipient's number.
                        String recipient_number = JOptionPane.showInputDialog(null, "Enter Recipient's Number:");

                        //Checks if the user's phone number is null.
                        if (recipient_number == null) {
                            JOptionPane.showMessageDialog(null, "Feature coming soon");
                            continue;//It will ship to the following code, it will start the while again
                        }

                        //Checks if the user's phone number is empty or its just space.
                        if (recipient_number.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "You must enter a recipient's number.");
                            continue;//It will ship to the following code, it will start the while again
                        }

                        //It removes the empty spaces in the phone number
                        message.setnumber(recipient_number.trim());

                        //Checks if the login phone number and the recipient phone number are the same,
                        //if they are asks the user if they want to send themselfs a message/s.
                        if (message.getnumber().equals(login.getPhone_number())) {
                            int choice = JOptionPane.showConfirmDialog(null, "Do you want to send yourself a message?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.NO_OPTION) {
                                continue;//It will ship to the following code, it will start the while again
                            }
                        }

                        //Check if the phone number meets the requirments, if not meet it will ask the user
                        //to input again until the requirments are meet.
                        if (!message.checkCellPhoneNumber(message.getnumber())) {
                            JOptionPane.showMessageDialog(null, "Invalid Cell Phone Number! (e.g. +27232348676)");
                            continue;//It will ship to the following code, it will start the while again
                        }
                        recipient_number_format = false;
                    }

                    boolean message_format = true;
                    while (message_format) {
                        //Asks the user to enter a message
                        String inputMessage = JOptionPane.showInputDialog(null, "Please enter a message (1 to 250 characters):");

                        //Checks if the user's phone number is null.
                        if (inputMessage == null) {
                            JOptionPane.showMessageDialog(null, "Feature coming soon");
                            continue;
                        }

                        //Check if the message meets the requirments, if not meet it will ask the user
                        //to input again until the requirments are meet.
                        message.setmessages(inputMessage);
                        if (inputMessage.trim().isEmpty()) {
                            message.checkmessage(message.getmessages());
                            continue;//It will ship to the following code, it will start the while again
                        }

                        //If the message meets the conditions it will be output options to the user and stored the message information inside a JSON file
                        if (message.checkmessage(message.getmessages()) == true) {
                            //Asks the user if they want to send/store/delete the message.
                            JOptionPane.showMessageDialog(null, message.SentMessage(random, count, message.getmessages(), message.getnumber()));

                            //stores the message information inside a JSON file.
                            message.storeMessages(random, count, message.getmessages(), message.getnumber());
                            message_format = false;
                        }
                    }

                }
            }
            if (selection == 1) {
                for (int i = 0; i < message.getsend().length; i++) {
                    if (message.getsend()[i] == null) {
                        JOptionPane.showMessageDialog(null, "Coming Soon.");
                        break;
                    }
                    if (message.getsend()[i] != null) {
                        int count = 0;
                        JOptionPane.showMessageDialog(null, message.printMessage(random, count, message.getmessages(), message.getnumber()));
                        break;
                    }
                }
            }
            if (selection == 2) {
                JOptionPane.showMessageDialog(null, "Total Number Of Messages Sent: " + message.returnTotalMessages(0));
                is_messaging = false;
            }
        }
    }
}
