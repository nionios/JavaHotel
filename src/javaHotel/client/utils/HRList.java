package javaHotel.client.utils;

import java.rmi.RemoteException;
import javaHotel.helpers.*;
import javaHotel.common.*;

public class HRList {
    public void list(String[] args, HR c) {
        SimplePrinter print = new SimplePrinter();
        try {
            if (args.length != 2) {
                print.out("Usage: java HRClient list [hostname]");
            } else {
                String inputHostname = args[1];
                String returnedInfo = c.list(inputHostname);
                print.out(returnedInfo);
            }
        } catch (RemoteException re) {
            print.out();
            print.out("RemoteException in list()");
            print.out(re);
        }
    }
}
