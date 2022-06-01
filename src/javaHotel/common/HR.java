package javaHotel.common;

public interface HR extends java.rmi.Remote {
    int prebook (String hostname, String type, int number, String name)
            throws java.rmi.RemoteException;

    Boolean book
   (String hostname, String type, int toBeBookedRooms, String name)
            throws java.rmi.RemoteException;

    Boolean cancel
   (String hostname, String inputType, int toBeCancelledRooms, String inputName)
            throws java.rmi.RemoteException;

    void notify(String inputHostname, String inputType, String InputName)
            throws java.rmi.RemoteException;

    String list(String hostname)
            throws java.rmi.RemoteException;

    String guests(String hostname)
            throws java.rmi.RemoteException;
}
