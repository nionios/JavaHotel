package javaHotel.client;

import javaHotel.helpers.*;
import javaHotel.client.utils.*;
import javaHotel.common.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class HRClient {

    private static void usage() {
        System.out.println("Usage: java HRClient [options] [suboptions]");
    }

    public static void main(String[] args) {
        SimplePrinter print = new SimplePrinter();
        try {
            HR c = (HR) Naming.lookup("rmi://localhost:7500/HRService");
            switch (args[0]) {
                // Choose method to run based on users argument
                case "book":
                    HRBooking book = new HRBooking();
                    book.startBookProcess(args, c);
                    break;
                case "list":
                    HRList available = new HRList();
                    available.list(args, c);
                    break;
                case "guests":
                    HRGuests guests = new HRGuests();
                    guests.showGuests(args, c);
                    break;
                case "cancel":
                    HRCancel cancel = new HRCancel();
                    cancel.cancelRooms(args, c);
                    break;
                default:
                    usage();
                    break;
            }
        }
        catch (RemoteException re) {
            print.out();
            print.out("RemoteException");
            print.out(re);
        }
        catch (MalformedURLException murle) {
            print.out();
            print.out( "MalformedURLException");
            print.out(murle);
        }
        catch (NotBoundException nbe) {
            print.out();
            print.out("NotBoundException");
            print.out(nbe);
        }
        catch (java.lang.ArithmeticException ae) {
            print.out();
            print.out("java.lang.ArithmeticException");
            print.out(ae);
        }
    }
}
