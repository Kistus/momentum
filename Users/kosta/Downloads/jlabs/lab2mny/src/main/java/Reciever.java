import java.util.ArrayList;
import java.util.List;

public class Reciever {
    private List<String> value = new ArrayList<>();

    public synchronized void put(String value) {
        this.value.add(value);
    }

    public void write(){ //z eksperymentow wlasnych widze ze lepiej wypisywac na koncu, a nie od razu, bo bedzie zamieszanie
        for (String str: this.value) {
            System.out.println(str);
        }
    }

}
