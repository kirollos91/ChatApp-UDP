import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;

public class TCPServer
{
    
    private static ServerSocket servSock;
    private static final int port = 1234;
    public static void main(String []args)
    {
   
        System.out.println("Connecting to port.....\n");

        try
        {
            servSock = new ServerSocket(port);
        }
        catch(Exception ex)
        {
            System.out.println("Unable to connect to port : 1234");
            System.exit(1);
        }
        
        do
        {
            handleClient();

        }
        while(true);
        



    }

    private static void handleClient()
    {
        Socket link = null;
        try
        {
            link = servSock.accept();
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(),true);

            int numMessage = 0;

            String message = input.nextLine();
            while(! message.equals("CLOSE"))
            {
                System.out.println("Message recieved");
                numMessage++;
                output.println("Message "+ numMessage + ": " + message);
                message = input.nextLine();
            }
            output.println(numMessage + " Message recieved."); 

        }
        catch(Exception ex)
        {
            System.out.println("");
        }

        try
        {
            System.out.println("Closing connection.....");
            link.close();
        }
        catch(Exception ex)
        {
            System.out.println("Unable Close.....");
            System.exit(1);
        }
    }

}

