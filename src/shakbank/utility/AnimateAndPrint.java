package shakbank.utility;

import static shakbank.utility.Print.print;
public final class AnimateAndPrint {
    private AnimateAndPrint() {}
    public static void animateAndPrint(String message, int delay) {
        for(String word : message.split(" ")) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            print(word + " ");
        }
    }

    public static void animateAndPrintln(String message, int delay) {
        animateAndPrint(message, delay);
        print("\n");
    }
}