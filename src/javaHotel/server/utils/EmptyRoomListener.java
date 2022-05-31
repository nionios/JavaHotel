package javaHotel.server.utils;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface
EmptyRoomListener extends Remote {
    void Trigger (String inputType, int inputNumber)
            throws RemoteException;
}
