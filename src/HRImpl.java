import java.util.*;

class BookingEntry {
    String TypeOfRooms;
    String CustomerName;
    int    NumberOfRooms;

    public BookingEntry
    (String InputType, int InputNumber, String InputName) {
        TypeOfRooms   = InputType;
        CustomerName  = InputName;
        NumberOfRooms = InputNumber;
    }

    public String getPrettyInfo() {
        String allInfo = "Booking Entry for customer " + CustomerName +
                         " : Booked " + NumberOfRooms +
                         " room(s) of type " + TypeOfRooms;
        return allInfo;
    }
}

public class HRImpl
extends java.rmi.server.UnicastRemoteObject
implements HR {
    int[] singleRooms = {25,60};
    int[] doubleRooms = {40,80};
    int[] twinRooms   = {20,90};
    int[] tripleRooms = {15,115};
    int[] quadRooms   = {10,140};
    List<BookingEntry> BookingList;

    private int getAvailableRooms (String type) {
        int availableRooms;
        switch (type) {
            case "A":
                availableRooms = singleRooms[0];
                break;
            case "B":
                availableRooms = doubleRooms[0];
                break;
            case "C":
                availableRooms = twinRooms[0];
                break;
            case "D":
                availableRooms = tripleRooms[0];
                break;
            case "E":
                availableRooms = quadRooms[0];
                break;
            default:
                System.out.println("Invalid Room Type");
                return -1;
        }
        return availableRooms;
    }

    private void subtractAvailableRooms
    (String type, int numberOfRoomsToSubstract) {
        switch (type) {
            case "A":
                singleRooms[0] -= numberOfRoomsToSubstract;
                break;
            case "B":
                doubleRooms[0] -= numberOfRoomsToSubstract;
                break;
            case "C":
                twinRooms[0]   -= numberOfRoomsToSubstract;
                break;
            case "D":
                tripleRooms[0] -= numberOfRoomsToSubstract;
                break;
            case "E":
                quadRooms[0]   -= numberOfRoomsToSubstract;
                break;
            default:
                System.out.println("Invalid Room Type");
                break;
        }
    }

    public HRImpl()
        throws java.rmi.RemoteException {
        BookingList = new ArrayList<BookingEntry>();
        //super();
    }

    public int prebook
    (String hostname, String type, int number, String name)
    throws java.rmi.RemoteException {
        System.out.println("==> Incoming request for method prebook():" +
                           "\n=> Request from "+ hostname +
                           " for " + number +
                           " room(s) of type '" + type + "'" +
                           " for customer '"     + name + "'" );
        /* Check the appropriate room type */
        int availableRooms = getAvailableRooms(type);
        System.out.println("* Rooms of type " + type +
                           " left: " + availableRooms);
        // Return number of available rooms to client
        return availableRooms;
    }

   public Boolean book
   (String hostname, String type, int toBeBookedRooms, String name)
       throws java.rmi.RemoteException {
        System.out.println("==> Incoming request for method book():" +
                           "\n=> Request from "+ hostname +
                           " for " + toBeBookedRooms +
                           " room(s) of type '" + type + "'" +
                           " for customer '"     + name + "'" );
       int availableRooms = getAvailableRooms(type);
       /* If available rooms are now less than expected, then another customer
       *  has booked in the period that the this one was prebooking, so check
       *  needs to be performed again to ensure there are enough rooms.*/
       if (toBeBookedRooms > availableRooms) return false;
       // If this goes through the continue with the booking of the rooms
       BookingEntry InputEntry =
           new BookingEntry(type, toBeBookedRooms, name);
       BookingList.add(InputEntry);
       // Subtract the amount of rooms booked
       subtractAvailableRooms(type,toBeBookedRooms);
       System.out.println("* Booked " + toBeBookedRooms +
               " room(s) of type " + type +
               " for customer " + name);
       return true;
   }

   public String list (String hostname)
       throws java.rmi.RemoteException {
        System.out.println("==> Incoming request for method list():" +
                           "\n=> Request from " + hostname);
        String info = " ** Info of all rooms in Java Hotel! **" +
                      "\n > " + singleRooms[0] + " of Single Rooms (type A)" +
                      " - price: " + singleRooms[1] + " per night" +
                      "\n > " + doubleRooms[0] + " of Double Rooms (type B)" +
                      " - price: " + doubleRooms[1] + " per night" +
                      "\n > " + twinRooms[0]   + " of Twin Rooms (type C)"   +
                      " - price: " + twinRooms[1] + " per night" +
                      "\n > " + tripleRooms[0] + " of Triple Rooms (type D)" +
                      " - price: " + tripleRooms[1] + " per night" +
                      "\n > " + quadRooms[0]   + " of Quad Rooms (type E)"   +
                      " - price: " + quadRooms[1] + " per night";
        return info;
   }

   public String guests (String hostname)
       throws java.rmi.RemoteException {
        System.out.println("==> Incoming request for method guests():" +
                           "\n=> Request from " + hostname);
        String info = " ** Info of all guests in Java Hotel! **";
        // Iterating over every Booking Entry and appending all info in var
        for (BookingEntry currentBookingEntry : BookingList)
            info += "\n" + currentBookingEntry.getPrettyInfo();
        return info;
   }
}
