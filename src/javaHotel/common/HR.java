package javaHotel.common;

public interface HR extends java.rmi.Remote {
    int prebook (String Hostname, String type, int number, String name)
            throws java.rmi.RemoteException;

    Boolean book
        (String Hostname, String type, int toBeBookedRooms, String name)
            throws java.rmi.RemoteException;

    Boolean cancel
        (String Hostname, String type, int toBeCancelledRooms, String name)
            throws java.rmi.RemoteException;

    void notify(String Hostname, String type, String name )
            throws java.rmi.RemoteException;

    String list(String Hostname)
            throws java.rmi.RemoteException;

    String guests(String Hostname)
            throws java.rmi.RemoteException;
}
