import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {

    public static void main(String []args) throws Exception {

        Random rd = new Random();
        Scanner scanner = new Scanner(System.in);
        int port = 12345;

        String message = "";

        Socket socket = new Socket("172.20.10.6", port);
        System.out.println("Client connected");

        InputStream in = socket.getInputStream();
        ObjectInputStream objectIn = new ObjectInputStream(in);

        OutputStream out = socket.getOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(out);

        while(true){
            message = objectIn.readObject().toString();
            if(message.equals("ready")){
                System.out.println("ready");
                break;
            }
        }

        message = scanner.nextLine();
        Integer n = Integer.parseInt(message);
        while (n < 2){
            message = scanner.nextLine();
            n = Integer.parseInt(message);
        }
        objectOut.writeObject(n);

        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) arr[i] = rd.nextInt();
        objectOut.writeObject(arr);

        while (true) {
            message = objectIn.readObject().toString();
            if(message.equals("ready for messages")){
                System.out.println("ready for messages");
                break;
            }
        }

        String output = "message";
        for(int i = 0; i < n; i++){
            objectOut.writeObject(new Message(i, output));
            Thread.sleep(1000);
        }

        while (true) {
            message = objectIn.readObject().toString();
            if (message.equals("finish")) {
                System.out.println("finish");
                break;
            }
        }

        objectOut.writeObject("kill");
        objectIn.close();
        objectOut.close();
        socket.close();
    }
}

