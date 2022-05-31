package javaHotel.helpers;

public class SimplePrinter {
    // Helper function to avoid writing "System.out.println" every time
    public void out(String strToPrint) {
        System.out.println(strToPrint);
    }
    // Overload for exceptions
    public void out(Exception excToPrint) {
        System.out.println(excToPrint);
    }
    // Overload for empty lines
    public void out() {
        System.out.println();
    }
}
