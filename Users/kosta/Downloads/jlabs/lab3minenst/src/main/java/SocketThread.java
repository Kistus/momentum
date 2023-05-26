import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread {
    public static void main(String[] args){

        int port = 12345;

        try(
                ServerSocket serverSocket = new ServerSocket(port)){
            while (true){
                System.out.println("Waiting for connection on port " + port);
                Socket fromClientSocket = serverSocket.accept();
                new Thread( new ThreadServer(fromClientSocket)).start();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}