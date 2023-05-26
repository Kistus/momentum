import java.util.ArrayList;
import java.util.List;

public class Sender{
    private List<Integer> value = new ArrayList<>();

    public synchronized int take() throws InterruptedException {
        while (value.isEmpty()) {
            wait();//Wait, maybe someone will put sth here.
        }
        return value.remove(0);
    }

    public synchronized void put(int value) {
        this.value.add(value);
        if(this.value.size()==1){
            notifyAll();
        }
    }
}
