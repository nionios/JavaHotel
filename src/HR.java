public interface HR extends java.rmi.Remote {
    public int prebook
        (String Hostname, String type, int number, String name)
        throws java.rmi.RemoteException;

    public Boolean book
        (String Hostname, String type, int toBeBookedRooms, String name)
        throws java.rmi.RemoteException;

    public String list (String Hostname)
        throws java.rmi.RemoteException;
}
