package Server;
import java.util.Scanner;

public class Driver {
    public static void main(String args [] ){

        Server server=new Server();

        Scanner scan=new Scanner(System.in);

        System.out.println("Enter 1 for Starting the Server !! \nEnter 0 for Terminating of the Sever !!");
        int choice =scan.nextInt();
        scan.close();
        try {
            server.startService(choice);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
