
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    private static InetAddress host;
    private static final int port = 1234;

    public static void main(String [] args)
    {
        
        
        try
        {
            host = InetAddress.getLocalHost();
        }
        catch(Exception ex)
        {
            System.out.println("Host not found !");
            System.exit(1);
        }
        accessServer();

    }
    private static void accessServer()
    {
        Socket link =null;
        Scanner usrEntry = new Scanner(System.in);
        try
        {
            link = new Socket(host,port);
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(),true);
            
            String message,response;
            do
            {
                System.out.print("Enter message: ");
                message = usrEntry.nextLine();
                output.println(message);
                response = input.nextLine();
                System.out.println("\nSREVER> " + response);

            }
            while(! message.equals("CLOSE"));
            

        }
        catch(Exception ex)
        {
            
        }
        try
        {
            System.out.println("Closing connection.....");
            link.close();
            usrEntry.close();
        }
        catch(Exception ex)
        {
            System.out.println("Unable to close!");
            System.exit(1);
        }   
    }
}