package javaHotel.client.utils;

import java.rmi.RemoteException;
import javaHotel.helpers.*;
import javaHotel.common.*;

public class HRCancel {
    public void cancelRooms(String[] args, HR c) {
        SimplePrinter print = new SimplePrinter();
        try {
            if (args.length != 5) {
                print.out("Usage: java HRClient cancel [hostname]" +
                          " [type] [number] [customer_name]");
            } else {
                String inputHostname = args[1];
                String inputType     = args[2];
                String inputCustomer = args[4];
                int    inputNumberRooms = Integer.parseInt(args[3]);
                if (c.cancel(inputHostname,
                             inputType,
                             inputNumberRooms,
                             inputCustomer))
                        print.out("* Cancelled " + inputNumberRooms +
                                  " rooms of type " + inputType +
                                  " for customer ");
                else print.out("! Could not cancel rooms! Are you sure you" +
                               " booked at least " + inputNumberRooms +
                               " of type " + inputType +
                               " under the customer name " + inputCustomer);
            }
        } catch (RemoteException re) {
            print.out();
            print.out("RemoteException in cancel()");
            print.out(re);
        }
    }
}
