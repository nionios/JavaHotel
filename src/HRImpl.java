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

    public HRImpl()
        throws java.rmi.RemoteException {
        super();
    }

    // TODO: Hostname variable?? Instanciate the list
    public void book(String hostname, String type, int number, String name)
        throws java.rmi.RemoteException {
        Boolean checked = false;
        /* Check the appropriate room type */
        switch (type) {
            case "A":
                if (singleRooms[0] > 0) checked = true;
                break;
            case "B":
                if (doubleRooms[0] > 0) checked = true;
                break;
            case "C":
                if (twinRooms[0] > 0) checked = true;
                break;
            case "D":
                if (tripleRooms[0] > 0) checked = true;
                break;
            case "E":
                if (quadRooms[0] > 0) checked = true;
                break;
            default:
                System.out.println("Invalid Room Type");
                return;
        }
        if (checked) {
            BookingEntry InputEntry = new BookingEntry(type, number, name);
            BookingList.add(InputEntry);
            System.out.println("Booked a room of type " + type +
                               " for customer " + name);
        } else {
            System.out.println("No more rooms of type " + type);
        }
    }
}
