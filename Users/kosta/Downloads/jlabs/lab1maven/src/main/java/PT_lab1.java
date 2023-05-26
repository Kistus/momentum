import java.util.*;

public class PT_lab1 {
    public static Map getStats(String sorting, Worker m1){
        Map stats;
        if (sorting.equals("natural")){
            stats=new TreeMap<>();
        }else{
            stats=new HashMap<>();
        }
        stats.put(m1,m1.getApprenticesAmount());
        return stats;
    }

    public static void main(String[] args){
        String arg= args.length > 0 ? args[0] : "default";

        Worker worker1 = new Worker("Marcus",9999, 1, args[0]);
        Worker worker2 = new Worker("James",999, 2, args[0]);
        Worker worker3 = new Worker("Adam",9, 4,  args[0]);
        Worker worker4 = new Worker("Patrick",99, 3,  args[0]);
        Worker worker5 = new Worker("Barbara", 999, 2,  args[0]);
        Worker worker6 = new Worker("Tony", 9, 4,  args[0]);
        Worker worker7 = new Worker("Steve",9, 4,  args[0]);
        Worker worker8 = new Worker("Maxim",9, 4,  args[0]);
        Worker worker9 = new Worker("Henry",99, 3,  args[0]);
        Worker worker10 = new Worker("Constantin",99, 3, args[0]);

        worker1.addApprentice(worker2);
        worker1.addApprentice(worker5);
        worker2.addApprentice(worker4);
        worker5.addApprentice(worker9);
        worker5.addApprentice(worker10);
        worker10.addApprentice(worker3);
        worker10.addApprentice(worker6);
        worker9.addApprentice(worker7);
        worker4.addApprentice(worker8);

        worker1.print();
        System.out.println();
        System.out.println(getStats(args[0], worker1));
    }
}
