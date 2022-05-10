import java.rmi.Naming;

public class HRServer {

   public HRServer() {
     try {
       HRinterface c = new HRImpl();
       Naming.rebind("rmi://localhost:7500/CalculatorService", c);
     } catch (Exception e) {
       System.out.println("Trouble: " + e);
     }
   }

   public static void main(String args[]) {
     new HRServer();
   }
}


