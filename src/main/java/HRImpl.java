public class HRImpl
    extends 
      java.rmi.server.UnicastRemoteObject 
    implements HRinterface {
 
    
    public HRImpl()
        throws java.rmi.RemoteException { 
        super(); 
    } 
 
    public long add(long a, long b) 
        throws java.rmi.RemoteException { 
        return a + b; 
    } 
 
    public long sub(long a, long b) 
        throws java.rmi.RemoteException { 
        return a - b; 
    } 
 
    public long mul(long a, long b) 
        throws java.rmi.RemoteException { 
        return a * b; 
    } 
 
    public long div(long a, long b) 
        throws java.rmi.RemoteException { 
        return a / b; 
    } 
}