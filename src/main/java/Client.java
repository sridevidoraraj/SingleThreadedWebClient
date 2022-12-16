import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class Client {
    private final static String HOSTNAME = "localhost";
    private final static int PORT = 5000;
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.asList(args).toString());
        File file = new File(args[0]);
        try{

        //Create Connection
        Socket s = new Socket("localhost",5000);
        System.out.println("[CONNECTED]");
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in   = new DataInputStream(s.getInputStream());


        String header = "GET / HTTP/1.1\r\n" + file
                +"Host:localhost\r\n\r\n";
        byte[] byteHeader = header.getBytes();
        out.write(byteHeader,0,header.length());

        String res = "";
        /////////////READ PROCESS/////////////
        byte[] buf = new byte[in.available()];
        in.readFully(buf);
        System.out.println("\t[READ PROCESS]");
        System.out.println("\t\tbuff length->"+buf.length);
        for(byte b : buf)
        {
            res += (char) b;
        }
        System.out.println("\t[/READ PROCESS]");
        /////////////END READ PROCESS/////////////

        System.out.println("[RES]");
        System.out.println(res);
        System.out.println("[CONN CLOSE]");

        in.close();
        out.close();
        s.close();


    }catch(Exception e)
    {
        e.printStackTrace();
    }
    }

}
