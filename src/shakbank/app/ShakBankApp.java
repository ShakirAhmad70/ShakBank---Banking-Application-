package shakbank.app;

import shakbank.services.BankServices;
import shakbank.utility.GetInput;

import static shakbank.utility.AnimateAndPrint.animateAndPrint;
import static shakbank.utility.AnimateAndPrint.animateAndPrintln;
import static shakbank.utility.Print.print;
import static shakbank.utility.Print.println;


public class ShakBankApp {
    public static void main(String[] args) {
        welcomeToShakBank();

        bankServicesMenu();
        print("Enter your choice: ");
        int choiceForMainMenu = GetInput.nextInt();
        while (true) {
            boolean exitSwitch = false;
            switch (choiceForMainMenu) {
                case 1 -> {
                    int index = BankServices.login();
                    while (index == -1) {
                        println("Invalid account number or password. Please try again...!");
                        print("Press 0 to go to main menu OR press any digit key to re-enter the details: ");
                        int whatToDoNow = GetInput.nextInt();
                        if (whatToDoNow == 0) {
                            exitSwitch = true;
                            break;
                        } else {
                            index = BankServices.login();
                        }
                    }
                    if (exitSwitch) break;
                    animateAndPrintln("Login successful !", 500);


                    animateAndPrintln("Hello " + BankServices.users.get(index).getAccountHolderName() + " !", 500);
                    menu();
                    print("Enter your choice: ");
                    int choice = GetInput.nextInt();
                    while(true) {
                        boolean exit = false;
                        switch (choice) {
                            case 0 -> BankServices.showUserDetails(index);
                            case 1 -> {
                                print("Enter amount to deposit: ");
                                double amount = GetInput.nextDouble();
                                while (amount <= 0) {
                                    print("Invalid amount. Please try again: ");
                                    amount = GetInput.nextDouble();
                                }
                                BankServices.deposit(index, amount);
                            }
                            case 2 -> {
                                print("Enter amount to withdraw: ");
                                double amount = GetInput.nextDouble();
                                while (amount <= 0 || amount > BankServices.users.get(index).getBalance()) {
                                    print("You have entered either negative amount or more than your balance. Please try again: ");
                                    amount = GetInput.nextDouble();
                                }
                                BankServices.withdraw(index, amount);
                            }
                            case 3 ->
                                    animateAndPrintln("Your balance is: " + BankServices.users.get(index).getBalance(), 300);
                            case 4 -> {
                                println("How would you like to transfer?");
                                println("\t1. Via Account Number");
                                println("\t2. Via Contact Number");
                                int choiceForTransfer = GetInput.nextInt();
                                while (choiceForTransfer != 1 && choiceForTransfer != 2) {
                                    print("Invalid choice. Please try again: ");
                                    choiceForTransfer = GetInput.nextInt();
                                }
                                String flag;
                                if (choiceForTransfer == 1) {
                                    print("Enter account number of receiver: ");
                                    String accountNumber = GetInput.next();
                                    print("Enter amount to transfer: ");
                                    double amount = GetInput.nextDouble();
                                    while (amount <= 0 || amount > BankServices.users.get(index).getBalance()) {
                                        print("You have entered either negative amount or more than your balance. Please try again: ");
                                        amount = GetInput.nextDouble();
                                    }
                                    flag = "account";
                                    BankServices.transferAmount(index, accountNumber, amount, flag);
                                } else {
                                    print("Enter contact number of receiver: ");
                                    String contactNumber = GetInput.next();
                                    print("Enter amount to transfer: ");
                                    double amount = GetInput.nextDouble();
                                    while (amount <= 0 || amount > BankServices.users.get(index).getBalance()) {
                                        print("You have entered either negative amount or more than your balance. Please try again: ");
                                        amount = GetInput.nextDouble();
                                    }
                                    flag = "contact";
                                    BankServices.transferAmount(index, contactNumber, amount, flag);
                                }

                            }
                            case 5 -> BankServices.changePassword(index);
                            case 6 -> BankServices.changeEmail(index);
                            case 7 -> BankServices.changeContactNo(index);
                            case 8 -> BankServices.changeAddress(index);
                            case 9 -> {
                                animateAndPrintln("Logout Successful, We are transferring you to main menu...", 300);
                                exit = true;
                            }
                            case 10 -> {
                                animateAndPrintln("Thanks for using ShakBank. Goodbye!", 300);
                                System.exit(0);
                            }
                            default -> animateAndPrintln("Invalid choice. Please try again.", 100);
                        }
                        if (exit) break;
                        menu();
                        print("Enter your choice: ");
                        choice = GetInput.nextInt();
                    }
                }


                case 2 -> BankServices.openAccount();
                case 3 -> BankServices.closeAccount();
                case 4 -> BankServices.showAllUsers();
                case 5 -> animateAndPrintln("Bank's Ifsc Code is: " + BankServices.getIfscCode(),100);
                case 6 -> {
                    animateAndPrintln("Thanks for using ShakBank. Goodbye!", 300);
                    System.exit(0);
                }
                default -> animateAndPrintln("Invalid choice. Please try again.", 100);
            }
            bankServicesMenu();
            print("Enter your choice: ");
            choiceForMainMenu = GetInput.nextInt();
        }
    }

    private static void welcomeToShakBank() {
        animateAndPrint("# # # # # # # ", 100);
        animateAndPrint(" Welcome to ShakBank ! ", 200);
        animateAndPrintln(" # # # # # # #", 100);

        animateAndPrint("# # # # # # # ", 100);
        animateAndPrint("We trust our customers", 200);
        animateAndPrintln(" # # # # # # #", 100);
    }

    private static void menu() {
        println("\nWhat would you like to do?");
        println("\t0. Show the details of your account");
        println("\t1. Deposit");
        println("\t2. Withdraw");
        println("\t3. Check Balance");
        println("\t4. Transfer Money");
        println("\t5. Change Password");
        println("\t6. Change Email");
        println("\t7. Change Contact Number");
        println("\t8. Change Address");
        println("\t9. Logout from your account");
        println("\t10. Exit from the application");
    }

    private static void bankServicesMenu() {
        println("\nWhat would you like to do?");
        println("1. Login to your account");
        println("2. Open a new account");
        println("3. Close the account");
        println("4. Show all users");
        println("5. Show the IFSC code of the bank");
        println("6. Exit the application");
    }
}