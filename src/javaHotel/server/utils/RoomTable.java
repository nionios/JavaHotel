package javaHotel.server.utils;

public class RoomTable {
    int availableRoomNumber;
    int RoomPrice;
    EmptyRoomListener tableListener;

    public RoomTable(int initNumber, int initPrice) {
        // TODO: what do I do with this??
        //tableListener = new EmptyRoomListener();
        availableRoomNumber = initNumber;
        RoomPrice = initPrice;
    }

    public void addRoomNumber(int inputNumber) {
        availableRoomNumber += inputNumber;
    }

    public int getAvailableRoomNumber() {
        return availableRoomNumber;
    }

    public int getRoomPrice() {
        return RoomPrice;
    }
}
