package javaHotel.common;

public interface
EmptyRoomListener extends java.rmi.Remote {
    void roomEmptiedTrigger (String inputType)
            throws java.rmi.RemoteException;
    String getRequestedRoomType ()
            throws java.rmi.RemoteException;
}
