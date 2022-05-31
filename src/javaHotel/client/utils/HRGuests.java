package javaHotel.client.utils;

import java.rmi.RemoteException;
import javaHotel.helpers.*;
import javaHotel.common.*;

public class HRGuests {
    public void showGuests (String[] args, HR c) {
        SimplePrinter print = new SimplePrinter();
        try {
            if (args.length != 2) {
                print.out("Usage: java HRClient guests [hostname]");
            } else {
                String inputHostname = args[1];
                String returnedInfo = c.guests(inputHostname);
                print.out(returnedInfo);
            }
        } catch (RemoteException re) {
            print.out();
            print.out("RemoteException in guests()");
            print.out(re);
        }
    }
}
