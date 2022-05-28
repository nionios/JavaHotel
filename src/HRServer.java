import java.rmi.Naming;

public class HRServer {
   public HRServer() {
     try {
       HR c = new HRImpl();
       Naming.rebind("rmi://localhost:7500/HRService", c);
     } catch (Exception e) {
       System.out.println("Trouble: " + e);
     }
   }
   public static void main(String args[]) {
     new HRServer();
   }
}
