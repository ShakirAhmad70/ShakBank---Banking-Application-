package shakbank.utility;

import java.util.InputMismatchException;
import java.util.Scanner;

import static shakbank.utility.Print.print;

public final class GetInput {
    private final static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    private GetInput() {
    }

    public static String nextLine() {
        String input = null;
        boolean flag = true;
        while (flag) {
            try {
                input = sc.nextLine();
                flag = false;
            } catch (InputMismatchException e) {
                print("Invalid input. Please try again: ");
            }
        }
        return input;
    }

    public static String next() {
        String input = null;
        boolean flag = true;
        while (flag) {
            try {
                input = sc.next();
                flag = false;
            } catch (InputMismatchException e) {
                print("Invalid input. Please try again: ");
            }
        }
        return input;
    }

    public static int nextInt() {
        int input = Integer.MIN_VALUE;
        boolean flag = true;
        while (flag) {
            try {
                input = sc.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                print("Invalid input. Please try again: ");
                sc.nextLine(); // Consume the invalid token
            }
        }
        return input;
    }

    public static double nextDouble() {
        double input = Double.MIN_VALUE;
        boolean flag = true;
        while (flag) {
            try {
                input = sc.nextDouble();
//                sc.nextLine();  //To Flush the buffer
                flag = false;
            } catch (InputMismatchException e) {
                print("Invalid input. Please try again: ");
                sc.nextLine(); // Consume the invalid token
            }
        }
        return input;
    }

//    public static char nextChar() {
//        char input = ' ';
//        boolean flag = true;
//        while (flag) {
//            try {
//                input = sc.next().charAt(0);
//                flag = false;
//            } catch (InputMismatchException e) {
//                print("Invalid input. Please try again: ");
//                sc.nextLine(); // Consume the invalid token
//            }
//        }
//        return input;
//    }

//    This method won't work in IDEs(Because System.console() will always return null)
//    public static char[] nextPassword() {
//        char[] input = new char[0];
//        Console console = System.console();
//        if (console == null) {
//            println("\nConsole not available, So exiting the application...!");
//            animateAndPrintln("Thanks for using ShakBank. Goodbye!", 300);
//            System.exit(0);
//        } else {
//            input = console.readPassword();
//        }
//        return input;
//    }
}