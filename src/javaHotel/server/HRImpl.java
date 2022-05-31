package javaHotel.server;

import javaHotel.common.*;
import javaHotel.helpers.*;
import javaHotel.server.utils.*;
import java.util.ArrayList;
import java.util.List;
import java.rmi.RemoteException;

public class HRImpl
extends java.rmi.server.UnicastRemoteObject
implements HR {
    List<BookingEntry> BookingList;
    List<BookingEntry> NotifyList;
    RoomDatabase RoomDB = new RoomDatabase();

    public HRImpl()
        throws java.rmi.RemoteException {
        BookingList = new ArrayList<BookingEntry>();
        NotifyList  = new ArrayList<BookingEntry>();
        //super();
    }

    private int getAvailableRooms (String type) {
        int availableRooms;
        switch (type) {
            case "A":
                availableRooms = RoomDB.singleRooms.getAvailableRoomNumber();
                break;
            case "B":
                availableRooms = RoomDB.doubleRooms.getAvailableRoomNumber();
                break;
            case "C":
                availableRooms = RoomDB.twinRooms.getAvailableRoomNumber();
                break;
            case "D":
                availableRooms = RoomDB.tripleRooms.getAvailableRoomNumber();
                break;
            case "E":
                availableRooms = RoomDB.quadRooms.getAvailableRoomNumber();
                break;
            default:
                SimplePrinter print = new SimplePrinter();
                print.out("Invalid Room Type");
                return -1;
        }
        return availableRooms;
    }

    private void addAvailableRooms
    (String type, int numberOfRooms) {
        switch (type) {
            case "A":
                RoomDB.singleRooms.addRoomNumber(numberOfRooms);
                break;
            case "B":
                RoomDB.doubleRooms.addRoomNumber(numberOfRooms);
                break;
            case "C":
                RoomDB.twinRooms.addRoomNumber(numberOfRooms);
                break;
            case "D":
                RoomDB.tripleRooms.addRoomNumber(numberOfRooms);
                break;
            case "E":
                RoomDB.quadRooms.addRoomNumber(numberOfRooms);
                break;
            default:
                SimplePrinter print = new SimplePrinter();
                //This should never fire
                print.out("Invalid Room Type");
                break;
        }
    }

    public int prebook
    (String hostname, String type, int number, String name)
    throws java.rmi.RemoteException {
        SimplePrinter print = new SimplePrinter();
        print.out("==> Incoming request for method prebook():" +
                  "\n=> Request from "+ hostname +
                  " for " + number +
                  " room(s) of type '" + type + "'" +
                  " for customer '"     + name + "'" );
        /* Check the appropriate room type */
        int availableRooms = getAvailableRooms(type);
        print.out("* Rooms of type " + type +
                  " left: " + availableRooms);
        // Return number of available rooms to client
        return availableRooms;
    }

   public Boolean book
   (String hostname, String type, int toBeBookedRooms, String name)
       throws java.rmi.RemoteException {
        SimplePrinter print = new SimplePrinter();
        print.out("==> Incoming request for method book():" +
                  "\n=> Request from "+ hostname +
                  " for " + toBeBookedRooms   +
                  " room(s) of type '" + type + "'" +
                  " for customer '"    + name + "'" );
       int availableRooms = getAvailableRooms(type);
       /* If available rooms are now less than expected, then another customer
       *  has booked in the period that this one was prebooking, so check
       *  needs to be performed again to ensure there are enough rooms.*/
       if (toBeBookedRooms > availableRooms) return false;
       // If this goes through continue with the booking of the rooms
       BookingEntry InputEntry =
           new BookingEntry(type, toBeBookedRooms, name);
       BookingList.add(InputEntry);
       // Subtract the amount of rooms booked (by adding negative room number)
       addAvailableRooms(type,-toBeBookedRooms);
       print.out("* Booked " + toBeBookedRooms +
                 " room(s) of type " + type +
                 " for customer " + name);
       return true;
   }


   public Boolean cancel
   (String hostname, String inputType, int toBeCancelledRooms, String inputName)
       throws java.rmi.RemoteException {
        SimplePrinter print = new SimplePrinter();
        print.out("==> Incoming request for method cancel():" +
                  "\n=> Request from "+ hostname +
                  " for " + toBeCancelledRooms +
                  " room(s) of type '" + inputType  + "'" +
                  " for customer '" + inputName  + "'" );
       int availableRooms = getAvailableRooms(inputType);
       if (toBeCancelledRooms < availableRooms) return false;
       // Look through the Booking List and delete the aproppriate entry
       for (BookingEntry currentBookingEntry : BookingList) {
           if (currentBookingEntry.getCustomerName().equals(inputName) ||
               currentBookingEntry.getType().equals(inputType))
               BookingList.remove(currentBookingEntry);
       }
       // Add the amount of rooms cancelled to available rooms
       addAvailableRooms(inputType,toBeCancelledRooms);
       print.out("* Cancelled " + toBeCancelledRooms +
                 " room(s) of type " + inputType +
                 " for customer " + inputName);
       return true;
   }

   public String list (String hostname)
       throws java.rmi.RemoteException {
        SimplePrinter print = new SimplePrinter();
        print.out("==> Incoming request for method list():" +
                  "\n=> Request from " + hostname);
        String info = " ** Info of all rooms in Java Hotel! **\n > " +
                      RoomDB.singleRooms.getAvailableRoomNumber()    +
                      " of Single Rooms (type A) - price: "          +
                      RoomDB.singleRooms.getRoomPrice()              +
                      " per night\n > "                              +
                      RoomDB.doubleRooms.getAvailableRoomNumber()    +
                      " of Double Rooms (type B) - price: "          +
                      RoomDB.doubleRooms.getRoomPrice()              +
                      " per night\n > "                              +
                      RoomDB.twinRooms.getAvailableRoomNumber()      +
                      " of Twin Rooms (type C) - price: "            +
                      RoomDB.twinRooms.getRoomPrice()                +
                      " per night\n > "                              +
                      RoomDB.tripleRooms.getAvailableRoomNumber()    +
                      " of Triple Rooms (type D) - price:"           +
                      RoomDB.tripleRooms.getRoomPrice()              +
                      " per night\n > "                              +
                      RoomDB.quadRooms.getAvailableRoomNumber()      +
                      " of Quad Rooms (type E) - price: "            +
                      RoomDB.quadRooms.getRoomPrice()                +
                      " per night";
        return info;
   }

   public String guests (String hostname)
       throws java.rmi.RemoteException {
        SimplePrinter print = new SimplePrinter();
        print.out("==> Incoming request for method guests():" +
                  "\n=> Request from " + hostname);
        String info = " ** Info of all guests in Java Hotel! **";
        // Iterating over every Booking Entry and appending all info in var
        for (BookingEntry currentBookingEntry : BookingList)
            info += "\n" + currentBookingEntry.getPrettyInfo();
        return info;
   }

   public void notify(String inputHostname, String inputType, String inputName)
       throws java.rmi.RemoteException {
        SimplePrinter print = new SimplePrinter();
        print.out("==> Incoming request for method notify():" +
                  "\n=> Request from " + inputHostname);
   }

   //private synchronized void notifyAvailabilityListeners
   //    (String inputType, int inputNumber) {
   //    for (EmptyRoomListener roomListener : getAvailableRooms(inputType)/*.keySet()*/) {
   //        try {
   //            roomListener.Trigger(inputType, inputNumber);
   //        } catch (RemoteException ex) {
   //            SimplePrinter print = new SimplePrinter();
   //            print.out("EmptyRoomListener is not responding");
   //            print.out(ex);
   //        }
   //    }
   //}

}
