import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException {
        int port = 8010; // port that listen to the user
        ServerSocket socket = new ServerSocket(port);//opens up a web socket at this port
        socket.setSoTimeout(10000);
        while(true){
            try {
                System.out.println("Server is Listening on port :"+ port);
                Socket acceptedConnection  = socket.accept();//when client request it will connect
                System.out.println("Connection accepted from the user :"+ acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());//out put stream from server side
                // to send message or anything- PrintWritter is used to convert into bytes and send to the stream
                //input stream from client side - bytes is given - BufferedReader is used to convert
                // these bytes to respective format and send to stream towards server
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedConnection.getInputStream())
                );
                toClient.println(("hello this is from  Server !!!!!! "));//toClient takes texts and convert to bytes
                toClient.close();
                fromClient.close();
                acceptedConnection.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {

        Server server = new Server();//as run is not static -i.e not in the memory so we have to create object
        try{
            server.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
