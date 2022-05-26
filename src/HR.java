public interface HR extends java.rmi.Remote {

    public void book(String Hostname, String type, int number, String name)
        throws java.rmi.RemoteException;
}
