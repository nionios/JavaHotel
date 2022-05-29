import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class HRClient {

    private static void booking (String[] args, HR c) {
    try {
        if (args.length != 5) {
           System.out.println("Usage: java HRClient book [hostname] [type] " +
                              "[number] [customer_name]");
        } else {
            String inputHostname = args[1];
            String inputType = args[2];
            int    inputNumberRooms = Integer.parseInt(args[3]);
            String inputCustomer = args[4];
            int availableReturnedRooms =
                c.prebook(inputHostname,
                          inputType,
                          inputNumberRooms,
                          inputCustomer);
            /* If less than enough rooms are available ask customer whether to
            *  book them or not */
            if (availableReturnedRooms < inputNumberRooms &&
                availableReturnedRooms != 0) {
                Scanner scan = new Scanner(System.in);
                while (true) {
                    System.out.println("* Only " + availableReturnedRooms +
                            " available for selected room type " + inputType +
                            ". Do you want to continue booking (y/n):");
                    String choice = scan.nextLine();
                    System.out.println("choice is " + choice);
                    if (choice.equals("y") || choice.equals("Y")) {
                        if (c.book(inputHostname,
                                   inputType,
                                   availableReturnedRooms,
                                   inputCustomer)) {
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
            } else if (availableReturnedRooms == 0) {
                System.out.println("! Sorry, no rooms left for room type " +
                                   inputType);
            } else {
                // If enough rooms are available just book them normally
                c.book(inputHostname,
                       inputType,
                       inputNumberRooms,
                       inputCustomer);
                System.out.println("* Booked "       + inputNumberRooms +
                                   " rooms of type " + inputType +
                                   " for customer "  + inputCustomer);
            }
         }
    } catch (RemoteException re) {
        System.out.println();
        System.out.println("RemoteException in booking()");
        System.out.println(re); }
    }

    private static void list(String[] args, HR c) {
        try {
            if (args.length != 2) {
                System.out.println("Usage: java HRClient list [hostname]");
            } else {
                String inputHostname = args[1];
                String returnedInfo = c.list(inputHostname);
                System.out.println(returnedInfo);
            }
        } catch (RemoteException re) {
            System.out.println();
            System.out.println("RemoteException in list()");
            System.out.println(re);
        }
    }

    private static void guests(String[] args, HR c) {
        try {
            if (args.length != 2) {
                System.out.println("Usage: java HRClient guests [hostname]");
            } else {
                String inputHostname = args[1];
                String returnedInfo = c.guests(inputHostname);
                System.out.println(returnedInfo);
            }
        } catch (RemoteException re) {
            System.out.println();
            System.out.println("RemoteException in guests()");
            System.out.println(re);
        }
    }

    private static void usage() {
        System.out.println("Usage: java HRClient [options] [suboptions]");
    }

    public static void main(String[] args) {
        try {
            HR c = (HR) Naming.lookup("rmi://localhost:7500/HRService");
            switch (args[0]) {
                // Choose method to run based on users argument
                case "book":
                    booking(args, c);
                    break;
                case "list":
                    list(args, c);
                    break;
                case "guests":
                    guests(args, c);
                    break;
                default:
                    usage();
                    break;
            }
        }
        catch (RemoteException re) {
            System.out.println();
            System.out.println("RemoteException");
            System.out.println(re);
        }
        catch (MalformedURLException murle) {
            System.out.println();
            System.out.println( "MalformedURLException");
            System.out.println(murle);
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

