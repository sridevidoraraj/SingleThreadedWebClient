import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final static String HOSTNAME = "localhost";
    private final static int PORT = 8080;

    public static void main(String[] args) throws IOException
    {
        try (Socket clientSocket = new Socket(HOSTNAME, PORT);
             InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
             BufferedReader in = new BufferedReader(isr);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            System.out.println("Connected to " + HOSTNAME + " on port " + PORT);

            String data = "Hello\nBye";

            System.out.println("Sending to server:\n" + data);
            out.println(data);

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Client received: " + line);
            }
        }
    }
}
