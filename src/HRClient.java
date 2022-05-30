import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class HRClient {
    // Helper function to avoid writing "System.out.println" every time
    private static void print(String strToPrint) {
        System.out.println(strToPrint);
    }
    // Overload for exceptions
    private static void print(Exception excToPrint) {
        System.out.println(excToPrint);
    }
    // Overload for empty lines
    private static void print() {
        System.out.println("");
    }

    private static void booking (String[] args, HR c) {
    try {
        if (args.length != 5) {
           print("Usage: java HRClient book [hostname] [type] [number] " +
                 "[customer_name]");
        } else {
            String inputHostname = args[1];
            String inputType     = args[2];
            String inputCustomer = args[4];
            int    inputNumberRooms = Integer.parseInt(args[3]);
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
                    print("* Only " + availableReturnedRooms +
                          " available for selected room type " + inputType +
                          ". Do you want to continue booking (y/n):");
                    String choice = scan.nextLine();
                    if (choice.equals("y") || choice.equals("Y")) {
                        if (c.book(inputHostname,
                                   inputType,
                                   availableReturnedRooms,
                                   inputCustomer)) {
                            print("* Booked " + availableReturnedRooms +
                                   " rooms of type " + inputType +
                                   " for customer "  + inputCustomer);
                        } else {
                            print("! Sorry, another client just" +
                                               "booked these rooms");
                        }
                        break;
                    }
                    else if (choice.equals("n") || choice.equals("N")) {break;}
                    else print("Please input 'y' or 'n'");
                }
                scan.close();
            } else if (availableReturnedRooms == 0) {
                print("! Sorry, no rooms left for room type " +
                                   inputType + "\n Do you want to be notified "
                                   + "when a room of type " + inputType +
                                   " becomes available?");
                Scanner scan = new Scanner(System.in);
                while (true) {
                    String choice = scan.nextLine();
                    if (choice.equals("y") || choice.equals("Y")) {
                        //TODO: implement this
                        //c.notify(inputType, inputNumberRooms);
                        break;
                    }
                    else if (choice.equals("n") || choice.equals("N")) {break;}
                    else print("Please input 'y' or 'n'");
                }
                scan.close();
            } else {
                // If enough rooms are available just book them normally
                c.book(inputHostname,
                       inputType,
                       inputNumberRooms,
                       inputCustomer);
                print("* Booked "       + inputNumberRooms +
                      " rooms of type " + inputType +
                      " for customer "  + inputCustomer);
            }
         }
    } catch (RemoteException re) {
        print();
        print("RemoteException in booking()");
        print(re); }
    }

    private static void list(String[] args, HR c) {
        try {
            if (args.length != 2) {
                print("Usage: java HRClient list [hostname]");
            } else {
                String inputHostname = args[1];
                String returnedInfo = c.list(inputHostname);
                print(returnedInfo);
            }
        } catch (RemoteException re) {
            print();
            print("RemoteException in list()");
            print(re);
        }
    }

    private static void guests(String[] args, HR c) {
        try {
            if (args.length != 2) {
                print("Usage: java HRClient guests [hostname]");
            } else {
                String inputHostname = args[1];
                String returnedInfo = c.guests(inputHostname);
                print(returnedInfo);
            }
        } catch (RemoteException re) {
            print();
            print("RemoteException in guests()");
            print(re);
        }
    }

    private static void cancel(String[] args, HR c) {
        try {
            if (args.length != 5) {
                print("Usage: java HRClient cancel [hostname]" +
                                   " [type] [number] [customer_name]");
            } else {
                String inputHostname = args[1];
                String inputType     = args[2];
                String inputCustomer = args[4];
                int    inputNumberRooms = Integer.parseInt(args[3]);
                if (c.cancel(inputHostname,
                             inputType,
                             inputNumberRooms,
                             inputCustomer)) print("*");
            }
        } catch (RemoteException re) {
            print();
            print("RemoteException in cancel()");
            print(re);
        }
    }

    private static void usage() {
        print("Usage: java HRClient [options] [suboptions]");
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
                case "cancel":
                    cancel(args, c);
                    break;
                default:
                    usage();
                    break;
            }
        }
        catch (RemoteException re) {
            print();
            print("RemoteException");
            print(re);
        }
        catch (MalformedURLException murle) {
            print();
            print( "MalformedURLException");
            print(murle);
        }
        catch (NotBoundException nbe) {
            print();
            print("NotBoundException");
            print(nbe);
        }
        catch (java.lang.ArithmeticException ae) {
            print();
            print("java.lang.ArithmeticException");
            print(ae);
        }
    }
}

