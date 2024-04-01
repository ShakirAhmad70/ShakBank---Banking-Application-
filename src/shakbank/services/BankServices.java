package shakbank.services;

import shakbank.beans.AccountType;
import shakbank.beans.Address;
import shakbank.beans.User;
import shakbank.utility.GetInput;
import shakbank.validators.ContactNoValidator;
import shakbank.validators.EmailValidator;
import shakbank.validators.PasswordValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static shakbank.utility.AnimateAndPrint.animateAndPrintln;
import static shakbank.utility.Print.print;
import static shakbank.utility.Print.println;

public final class BankServices {

    public static List<User> users = new ArrayList<>();

    public static final String ifscCode;

    static {
        ifscCode = "SHAK0006969";
    }

    private BankServices() {
    }

    public static int login() {
        print("Enter your account number: ");
        String accountNumber = GetInput.next();
        print("Enter your password: ");
//        char[] password = GetInput.nextPassword();
        String password = GetInput.next();
        int index = -1;
        for (User user : users) {
            if (user.getAccountNumber().equalsIgnoreCase(accountNumber) && user.getPassword().equals(password)) {
                index = users.indexOf(user);
                break;
            }
        }
        return index;
    }

    public static void openAccount() {
        animateAndPrintln("Account creation is about to begin", 300);

        print("Enter your name: ");
        GetInput.nextLine(); //Flush
        String accountHolderName = GetInput.nextLine();

        print("Enter your father's name: ");
        String fatherName = GetInput.nextLine();

        print("Enter your email: ");
        String email = GetInput.next().toLowerCase();
        while (EmailValidator.validate(email)) {
            print("This email is not acceptable. Please try again: ");
            email = GetInput.next().toLowerCase();
        }

        print("Enter your contact number: ");
        GetInput.nextLine(); //Flush the buffer
        String contactNo = GetInput.next();
        GetInput.nextLine(); //Flush the buffer
        boolean isNumberAlreadyRegistered;
        do {
            isNumberAlreadyRegistered = false; // Reset flag for each loop iteration

            while (ContactNoValidator.validate(contactNo)) {
                print("This contact number is not acceptable. Please try again: ");
                contactNo = GetInput.next();
                GetInput.nextLine(); //Flush
            }

            for (User user : users) {
                if (user.getContactNo().equals(contactNo)) {
                    print("This contact number is already registered. Please try again: ");
                    isNumberAlreadyRegistered = true;
                    contactNo = GetInput.next();
                    GetInput.nextLine(); //Flush
                    break; // Exit the for each loop early if the number is already registered
                }
            }
        } while (isNumberAlreadyRegistered);


        print("What is your address?\n" +
                "Village: ");
        String village = GetInput.nextLine();
        print("City: ");
        String city = GetInput.nextLine();
        print("State: ");
        String state = GetInput.nextLine();
        print("Country: ");
        String country = GetInput.nextLine();
        print("Pin code: ");
        String pinCode = GetInput.nextLine();
        Address address = new Address(village, city, state, country, pinCode);

        print("What type of account you want to open?\n1. Saving Account\n2. Current Account\n");
        int accountChoice = GetInput.nextInt();
        while (accountChoice != 1 && accountChoice != 2) {
            print("Invalid choice. Please try again: ");
            accountChoice = GetInput.nextInt();
        }
        AccountType accountType;
        if (accountChoice == 1) {
            accountType = AccountType.SAVING;
        } else {
            accountType = AccountType.CURRENT;
        }

        print("Enter initial balance: ");
        double balance = GetInput.nextDouble();
        while (balance < 0) {
            print("Balance can not be negative. Please try again: ");
            balance = GetInput.nextDouble();
        }

        //password must be of minimum 8 length and must contain a capital letter and a small letter and digits and must not contain spaces
        print("Enter you password (password must be of minimum 8 length and must contain a capital letter and a small letter and digits and must not contain spaces) : ");
        String password = GetInput.next();
        while (PasswordValidator.validate(password)) {
            print("This password is not acceptable (password must be of minimum 8 length and must contain a capital letter and a small letter and digits and must not contain spaces). Please try again: ");
            password = GetInput.next();
        }

        User newUser = new User(accountHolderName, fatherName, password, email, contactNo, address, accountType, balance);
        users.add(newUser);

        animateAndPrintln("Your account has been opened successfully!", 300);
        println("Now note down your account number somewhere safe for further use: \"" + newUser.getAccountNumber() + "\"");
    }

    public static void closeAccount() {
        println("Are you sure you want to close your account(Y - for yes/N - for no)? : ");
//        int choice = GetInput.nextChar();
        int choice = new Scanner(System.in).next().charAt(0);
        while(choice != 'Y' && choice != 'N' && choice != 'y' && choice != 'n') {
            print("Invalid choice. Please try again: ");
//            choice = GetInput.nextInt();
            choice = new Scanner(System.in).next().charAt(0);
        }
        if (choice == 'Y' || choice == 'y') {
            int userIndex = BankServices.login();
            if (userIndex == -1) {
                animateAndPrintln("No user found with these details...",100);
                return;
            }
            users.remove(users.get(userIndex));
            animateAndPrintln("Account closed successfully!", 300);
        } else {
            animateAndPrintln("Thanks for being with us!, Account not closed", 300);
        }
    }

    public static void deposit(int index, double amount) {
        users.get(index).setBalance(users.get(index).getBalance() + amount);
        animateAndPrintln("Amount deposited successfully!", 300);
    }

    public static void withdraw(int index, double amount) {
        if (amount > users.get(index).getBalance()) {
            animateAndPrintln("Insufficient balance!", 300);
        } else {
            users.get(index).setBalance(users.get(index).getBalance() - amount);
            animateAndPrintln("Amount withdrawn successfully!", 300);
        }
    }

    public static void transferAmount(int indexFrom, String ToNumber, double amount, String flag) {
        if (flag.equals("account")) {
            users.get(indexFrom).setBalance(users.get(indexFrom).getBalance() - amount);
            for(User user : users){
                if(user.getAccountNumber().equals(ToNumber)){
                    user.setBalance(user.getBalance() + amount);
                    animateAndPrintln("Amount transferred successfully!", 300);
                } else {
                    animateAndPrintln("Account not found!", 300);
                }
                return;
            }
        } else if (flag.equals("contact")) {
            users.get(indexFrom).setBalance(users.get(indexFrom).getBalance() - amount);
            for(User user : users){
                if(user.getContactNo().equals(ToNumber)){
                    user.setBalance(user.getBalance() + amount);
                    animateAndPrintln("Amount transferred successfully!", 300);
                } else {
                    animateAndPrintln("Account not found!", 300);
                }
                return;
            }
        }
    }

    public static void changePassword(int index) {
        print("Enter new password: ");
        String password = GetInput.next();
        while (PasswordValidator.validate(password)) {
            print("This password is not acceptable. Please try again: ");
            password = GetInput.next();
        }
        users.get(index).setPassword(password);
        animateAndPrintln("Password changed successfully!", 300);
    }

    public static void changeEmail(int index) {
        print("Enter new email: ");
        String email = GetInput.next().toLowerCase();
        while (EmailValidator.validate(email)) {
            print("This email is not acceptable. Please try again: ");
            email = GetInput.next().toLowerCase();
        }
        users.get(index).setEmail(email);
        animateAndPrintln("Email changed successfully!", 300);
    }

    public static void changeContactNo(int index) {
        print("Enter new contact number: ");
        String contactNo = GetInput.next();
        boolean isNumberAlreadyRegistered;
        do {
            isNumberAlreadyRegistered = false; // Reset flag for each loop iteration

            while (ContactNoValidator.validate(contactNo)) {
                print("This contact number is not acceptable. Please try again: ");
                contactNo = GetInput.next();
            }

            for (User user : users) {
                if (user.getContactNo().equals(contactNo)) {
                    print("This contact number is already registered. Please try again: ");
                    isNumberAlreadyRegistered = true;
                    contactNo = GetInput.next();
                    break; // Exit the for each loop early if the number is already registered
                }
            }
        } while (isNumberAlreadyRegistered);

        users.get(index).setContactNo(contactNo);
        animateAndPrintln("Contact number changed successfully!", 300);
    }

    public static void changeAddress(int index) {
        print("What is your new address?\n" +
                "Village: ");
        String village = GetInput.nextLine();
        print("City: ");
        String city = GetInput.nextLine();
        print("State: ");
        String state = GetInput.nextLine();
        print("Country: ");
        String country = GetInput.nextLine();
        print("Pin code: ");
        String pinCode = GetInput.nextLine();
        Address address = new Address(village, city, state, country, pinCode);
        users.get(index).setAddress(address);
        animateAndPrintln("Address changed successfully!", 300);
    }

    public static void showUserDetails(int index) {
        println(users.get(index));
    }

    public static void showAllUsers() {
        if (users.isEmpty()) {
            animateAndPrintln("There is no user yet!", 200);
            return;
        }
        int i=1;
        for(User user : users){
            println(i++ + ".Name: " + user.getAccountHolderName() + ",  AccNo.: " + user.getAccountNumber());
        }
    }

    public static String getIfscCode() {
        return ifscCode;
    }
}