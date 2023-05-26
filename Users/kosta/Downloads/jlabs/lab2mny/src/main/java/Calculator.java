import java.lang.Math;
import java.util.Random;

public class Calculator implements Runnable{
    private Reciever rec;
    private Sender send;
    private int checkedValue;

    public Calculator(Reciever rec, Sender send){
        this.rec = rec;
        this.send = send;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                this.checkedValue = send.take();
                boolean isPrime = true;
                for(int i = 2; i< Math.sqrt(this.checkedValue); i++){
                    if(this.checkedValue%i==0){
                        isPrime = false;
                        break;
                    }
                }
                Random rand = new Random();
                Thread.sleep((rand.nextInt(20)+10)*100); //symulacja roznego czasu dzialania watkow
                this.rec.put(this.checkedValue + " is"+ (isPrime?" ":" not ") +"prime");
            }catch (InterruptedException e) {
                break;
            }
        }
    }
}
