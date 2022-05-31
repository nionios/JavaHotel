package javaHotel.server.utils;

public class BookingEntry {
    String TypeOfRooms;
    String CustomerName;
    int    NumberOfRooms;

    public BookingEntry
    (String InputType, int InputNumber, String InputName) {
        TypeOfRooms   = InputType;
        CustomerName  = InputName;
        NumberOfRooms = InputNumber;
    }

    public String getType() { return TypeOfRooms; }
    public String getCustomerName() { return CustomerName; }

    public String getPrettyInfo() {
        String allInfo = "Booking Entry for customer " + CustomerName +
                         " : Booked " + NumberOfRooms +
                         " room(s) of type " + TypeOfRooms;
        return allInfo;
    }
}
