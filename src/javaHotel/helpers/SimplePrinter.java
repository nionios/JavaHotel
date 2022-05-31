package javaHotel.helpers;
public class SimplePrinter {
    // Helper function to avoid writing "System.out.println" every time
    public static void out(String strToPrint) {
        System.out.println(strToPrint);
    }
    // Overload for exceptions
    public static void out(Exception excToPrint) {
        System.out.println(excToPrint);
    }
    // Overload for empty lines
    public static void out() {
        System.out.println();
    }
}
