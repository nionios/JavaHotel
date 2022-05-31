package javaHotel.client.utils;

import javaHotel.common.*;
import javaHotel.helpers.*;
import java.rmi.RemoteException;
import java.util.Scanner;

public class HRBooking {
    public void startBookProcess (String[] args, HR c) {
        SimplePrinter print = new SimplePrinter();
        try {
            if (args.length != 5) {
                print.out("Usage: java HRClient book [hostname] [type] [number] " +
                        "[customer_name]");
            } else {
                String inputHostname = args[1];
                String inputType     = args[2];
                String inputCustomer = args[4];
                int    inputNumberRooms = Integer.parseInt(args[3]);
                int availableReturnedRooms =
                    c.prebook(inputHostname,
                            inputType,
                            inputNumberRooms,
                            inputCustomer);
                /* If less than enough rooms are available ask customer whether to
                 *  book them or not */
                if (availableReturnedRooms < inputNumberRooms &&
                        availableReturnedRooms != 0) {
                    Scanner scan = new Scanner(System.in);
                    while (true) {
                        print.out("* Only " + availableReturnedRooms +
                                " available for selected room type " + inputType +
                                ". Do you want to continue booking (y/n):");
                        String choice = scan.nextLine();
                        if (choice.equals("y") || choice.equals("Y")) {
                            if (c.book(inputHostname,
                                        inputType,
                                        availableReturnedRooms,
                                        inputCustomer)) {
                                print.out("* Booked " + availableReturnedRooms +
                                          " rooms of type " + inputType +
                                          " for customer "  + inputCustomer);
                            } else {
                                print.out("! Sorry, another client just" +
                                          "booked these rooms");
                            }
                            break;
                        }
                        else if (choice.equals("n") || choice.equals("N")) {break;}
                        else print.out("Please input 'y' or 'n'");
                    }
                    scan.close();
                } else if (availableReturnedRooms == 0) {
                    print.out("! Sorry, no rooms left for room type " +
                              inputType + "\n Do you want to be notified "
                              + "when a room of type " + inputType +
                              " becomes available?");
                    Scanner scan = new Scanner(System.in);
                    while (true) {
                        String choice = scan.nextLine();
                        if (choice.equals("y") || choice.equals("Y")) {
                            c.notify(inputHostname,
                                     inputType,
                                     inputNumberRooms);
                            break;
                        }
                        else if (choice.equals("n") || choice.equals("N")) {break;}
                        else print.out("Please input 'y' or 'n'");
                    }
                    scan.close();
                } else {
                    // If enough rooms are available just book them normally
                    c.book(inputHostname,
                            inputType,
                            inputNumberRooms,
                            inputCustomer);
                    print.out("* Booked "       + inputNumberRooms +
                              " rooms of type " + inputType +
                              " for customer "  + inputCustomer);
                }
            }
        } catch (RemoteException re) {
            print.out();
            print.out("RemoteException in booking()");
            print.out(re);
        }
    }
}
