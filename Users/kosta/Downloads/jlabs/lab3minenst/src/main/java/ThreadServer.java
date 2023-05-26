import java.io.*;
import java.net.Socket;
import java.util.Arrays;


public class ThreadServer implements Runnable{

    private Socket socket;
    private int n = 0;
    private int arr[];

    public ThreadServer(Socket fromClientSocket) {
        this.socket = fromClientSocket;
    }

    @Override
    public void run() {
        try (
                Socket localSocket = socket;
                OutputStream out = localSocket.getOutputStream();
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                InputStream in = localSocket.getInputStream();
                ObjectInputStream objectIn = new ObjectInputStream(in)) {

            System.out.println("Thread started");
            objectOut.writeObject("ready");

            while(true){
                this.n = (Integer) objectIn.readObject();
                if(this.n > 0) {
                    System.out.println("n:" + this.n);
                    break;
                }
            }
            arr = (int[]) objectIn.readObject();

            objectOut.writeObject("ready for messages");

            for ( int i = 0; i< n; i++){
                Message mes = (Message) objectIn.readObject();
                System.out.println("number: " + mes.getNumber() + ", content: " + mes.getContent());
            }
            objectOut.writeObject("finish");

            while(true){
                String str = (String) objectIn.readObject();
                if(str.equals("kill")) {
                    System.out.println(Arrays.toString(arr));
                    System.out.println("Thread end");
                    break;
                }
            }
            objectIn.close();
            objectOut.close();
            localSocket.close();
        }catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}