import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.net.MalformedURLException; 
import java.rmi.NotBoundException; 
 
public class HRClient { 
 
    public static void main(String[] args) { 
        try { 
            HR c = (HR)
            Naming.lookup("rmi://localhost:7500/HRService");
            c.book("localhost","E",4,"Dionisis");
        }
        catch (MalformedURLException murle) { 
            System.out.println(); 
            System.out.println( "MalformedURLException");
            System.out.println(murle);
        }
        catch (RemoteException re) {
            System.out.println();
            System.out.println("RemoteException");
            System.out.println(re); 
        }
        catch (NotBoundException nbe) {
            System.out.println();
            System.out.println("NotBoundException");
            System.out.println(nbe);
        }
        catch (java.lang.ArithmeticException ae) {
            System.out.println();
            System.out.println("java.lang.ArithmeticException");
            System.out.println(ae);
        } 
    } 
} 

