package javaHotel.server.utils;

public class RoomDatabase {
    public RoomTable singleRooms;
    public RoomTable doubleRooms;
    public RoomTable twinRooms;
    public RoomTable tripleRooms;
    public RoomTable quadRooms;

    public RoomDatabase  (){
        singleRooms = new RoomTable(25,60);
        doubleRooms = new RoomTable(40,80);
        twinRooms   = new RoomTable(20,90);
        tripleRooms = new RoomTable(15,115);
        quadRooms   = new RoomTable(10,140);
    }
}
