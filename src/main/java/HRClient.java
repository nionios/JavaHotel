import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.net.MalformedURLException; 
import java.rmi.NotBoundException; 
 
public class HRClient {
 
    public static void main(String[] args) { 
        try { 
            HRinterface c = (HRinterface)
                           Naming.lookup(
                 "rmi://mpi9:7500/CalculatorService"); 
            System.out.println( c.sub(9, 3) ); 
            System.out.println( c.add(9, 3) ); 
            System.out.println( c.mul(9, 3) ); 
            System.out.println( c.div(9, 3) ); 
        } 
        catch (MalformedURLException murle) { 
            System.out.println(); 
            System.out.println(
              "MalformedURLException"); 
            System.out.println(murle); 
        } 
        catch (RemoteException re) { 
            System.out.println(); 
            System.out.println(
                        "RemoteException"); 
            System.out.println(re); 
        } 
        catch (NotBoundException nbe) { 
            System.out.println(); 
            System.out.println(
                       "NotBoundException"); 
            System.out.println(nbe); 
        } 
        catch (
            java.lang.ArithmeticException
                                      ae) { 
            System.out.println(); 
            System.out.println(
             "java.lang.ArithmeticException"); 
            System.out.println(ae); 
        } 
    } 
} 

