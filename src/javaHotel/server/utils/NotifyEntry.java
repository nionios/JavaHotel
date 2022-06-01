package javaHotel.server.utils;

public class NotifyEntry {
    String TypeOfRooms;
    String CustomerName;

    public NotifyEntry
    (String InputType, String InputName) {
        TypeOfRooms   = InputType;
        CustomerName  = InputName;
    }

    public String getType() { return TypeOfRooms; }
    public String getCustomerName() { return CustomerName; }
}
