package javaHotel.server.utils;

public class RoomTable {
    int availableRoomNumber;
    int RoomPrice;

    public RoomTable(int initNumber, int initPrice) {
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
