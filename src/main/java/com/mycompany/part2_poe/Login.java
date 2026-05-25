/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part2_poe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 *
 * @author RC_Student_lab
 */
public class Login {

    //Declarations
    private String Firstname;
    private String Lastname;
    private String Username;
    private String Password;
    private String Phone_number;
    private String login_username;
    private String login_password;

    //Firstname
    public String getFirstname() {

        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    //Lastname
    public String getLastname() {

        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    //username
    public String getUsername() {

        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    //password
    public String getPassword() {

        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    //phone number
    public String getPhone_number() {

        return Phone_number;
    }

    public void setPhone_number(String phone_number) {

        this.Phone_number = phone_number;
    }

    //login username
    public String getLogin_username() {

        return login_username;
    }

    public void setLogin_username(String login_username) {

        this.login_username = login_username;
    }

    //login password
    public String getLogin_password() {

        return login_password;
    }

    public void setLogin_password(String login_password) {

        this.login_password = login_password;
    }
    
    //Checking username if it's valid or not
    public boolean checkUserName(String Username){
        if(Username.contains("_") && Username.length() < 6){
            JOptionPane.showMessageDialog(null, "Username successfully captured");
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your" +
            " username contains an underscore and is no more than five characters in length.");
            return false;
        }  
    }

    //Checking password if it's valid or not
    public  boolean checkPasswordComplexity(String password){

        //password
        String special_character = "[@&^$?/*%_()=<>:;!]";
        String digit = "\\d";
        String caps = "[A-Z]";

        Pattern pattern_special_character = Pattern.compile(special_character);
        Pattern pattern_digit = Pattern.compile(digit);
        Pattern pattern_caps = Pattern.compile(caps);

        Matcher digit_matcher = pattern_digit.matcher(password);
        Matcher special_character_matcher = pattern_special_character.matcher(password);
        Matcher caps_matcher = pattern_caps.matcher(password);

        if ((digit_matcher.find() && special_character_matcher.find() && caps_matcher.find()) && password.length() >= 8){
            
            JOptionPane.showMessageDialog(null, "Password successfully captured");
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the" +
                    " password contains at least eight characters, a number, and a special character.");
            return false;
        }
    }

    //Checking cell phone number if it's valid or not
    public  boolean checkCellPhoneNumber(String phone_number){

        Pattern international_code = Pattern.compile("^\\+27\\d{9}$");
        Matcher cell_phone_number = international_code.matcher(phone_number);

        if (cell_phone_number.matches()) {
            JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code.");
            return false;
        }
    }

    //register user
    public String registerUser(boolean is_username_valid, boolean is_password_valid){
        if (!is_username_valid && !is_password_valid){
            
            return "Username and password do not meet the requirements.\n";
        }
        else if (!is_username_valid){
            return "Username does not meet the requirements.\n";
        }
        else if (!is_password_valid){
            return "The password does not meet the complexity requirements.\n";
        }
        else {
            return "Registered Successfully.\n";
        }
    }

    //login
    public boolean loginUser(String temp_username, String temp_password,  String login_username, String login_password){

        return login_username.equals(temp_username) && login_password.equals(temp_password);

    }

    public String returnLoginStatus(String tempUsername, String temp_password, String login_username, String login_password, String Firstname, String Lastname){
        if (loginUser(tempUsername, temp_password, login_username, login_password)){
            return "Welcome " + Firstname + ", " + Lastname + " it is great to see you again.\n";
        }
        else {
            return "Username or password incorrect please try again.\n";
        }
    }

}
