import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PT_lab2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Reciever reciever = new Reciever();
        Sender sender = new Sender();
        if (Integer.parseInt(args[0]) != 0) {

            List<Thread> calculators = new ArrayList<>();

            for(int i = 0; i < Integer.parseInt(args[0]); i++){
                Thread thread = new Thread(new Calculator(reciever, sender));
                calculators.add(thread);
                thread.start();
            }

            String input = sc.nextLine();
            while (!input.equals("quit")) {
                int number = Integer.parseInt(input);
                if (number > 0) {
                    sender.put(number);
                }
                input = sc.nextLine();
            }
            reciever.write();
            for(Thread thread : calculators){
                thread.interrupt();
            }
        }
    }
}
