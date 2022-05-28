import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class HRClient {

    public static void main(String[] args) {
        try {
            HR c = (HR)
            Naming.lookup("rmi://localhost:7500/HRService");
            int inputNumberRooms = 40;
            String inputCustomer = "Dionisis Nikolopoulos";
            String inputType = "E";
            String inputHostname = "localhost";
            int availableReturnedRooms =
                c.prebook(inputHostname,
                          inputType,
                          inputNumberRooms,
                          inputCustomer);
            if (availableReturnedRooms < inputNumberRooms) {
                Scanner scan = new Scanner(System.in);
                while (true) {
                    System.out.println("* Only " + availableReturnedRooms +
                            " available for selected room type " + inputType +
                            ". Do you want to continue booking (y/n):");
                    String choice = scan.nextLine();
                    System.out.println("choice is " + choice);
                    if (choice.equals("y") || choice.equals("Y")) {
                        if (c.book(
                            "localhost","E",availableReturnedRooms,"Dionisis")){
                            System.out.println("* Booked " +
                                                availableReturnedRooms +
                                                " rooms of type " + inputType +
                                                " for customer "  +
                                                inputCustomer);
                        } else {
                            System.out.println("! Sorry, another client just" +
                                               "booked these rooms");
                        }
                        break;
                    }
                    else if (choice.equals("n") || choice.equals("N")) {break;}
                    else System.out.println("Please input 'y' or 'n'");
                }
                scan.close();
            }
            String returnedInfo = c.list(inputHostname);
            System.out.println(returnedInfo);
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

